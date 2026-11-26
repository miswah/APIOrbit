Here is a complete, production-ready Architecture Design Document (ADD) tailored for your standalone Spring Boot BIRT reporting service.

Architecture Design Document: Ephemeral .rptdocument Caching Layer

• Status: Proposed / Ready for Implementation
• Target Runtime: Standalone Spring Boot (Single-Node Deployment)
• Core Stack: Java 17+, Spring Boot 3.x, Eclipse BIRT Engine, Caffeine Cache, Micrometer

1. Context & Problem Statement

The reporting service generates BIRT .rptdocument intermediate artifacts (Run phase) so users can interactively paginate, inspect, and export reports to multiple formats (Render phase—PDF, XLSX, HTML) without re-executing expensive database queries.
• Constraints:
	• Standalone deployment without Docker or external infrastructure (e.g., standalone Redis).
	• Ephemeral cache: Data does not need to survive application restarts.
	• Strict 1-hour TTL per generated artifact.
	• Zero baseline telemetry: Average artifact size (ranging from KBs to hundreds of MBs) and concurrency patterns are currently unknown.
• Problem: Storing raw .rptdocument byte arrays inside the JVM heap (via Caffeine) causes severe Garbage Collection (GC) pauses and OutOfMemoryError crashes as artifact sizes scale.

2. Architectural Decision

Adopt a Hybrid Two-Tier Ephemeral Storage Architecture (Local Disk Storage + In-Heap Metadata Index).
1. Data Plane (Local Filesystem / OS Page Cache): BIRT's IRunTask streams binary .rptdocument archives directly to a dedicated local temp directory. This avoids JVM heap allocation entirely and requires zero extra Java object serialization. Modern OS kernels automatically cache recently written files in unused physical RAM (OS Page Cache), providing memory-speed reads for active reports.
2. Control Plane (Caffeine In-Heap Index): Caffeine stores only lightweight pointer objects (ReportArtifactMetadata, ~250 bytes each) mapping a unique reportExecutionId to its file path on disk, enforcing the 1-hour TTL and triggering automatic file deletion upon eviction.

3. System Architecture & Lifecycle Flow

Show Code
Download diagram SVG

Lifecycle Phases

1. Startup Hygiene (@PostConstruct): On boot, the application wipes any residual directory from previous runs and recreates a clean workspace (${java.io.tmpdir}/birt-rpt-cache).
2. Artifact Generation (Run): IRunTask writes directly to {uuid}.rptdocument.tmp, then atomically renames it to {uuid}.rptdocument upon completion to prevent partial reads.
3. Cache Registration: The completed Path and file size are registered in Caffeine with expireAfterWrite = 1 hour.
4. Artifact Consumption (Render): Subsequent export/pagination requests look up the Path in Caffeine, acquire a read lock, and open an IReportDocument stream via BIRT's IDocArchiveReader.
5. Eviction & Cleanup: When 1 hour elapses (or maximum entry/disk limits are reached), Caffeine triggers a RemovalListener that deletes the underlying file. If a Render task is actively reading the file during eviction, a reference counter defers deletion until the stream closes.

4. Concurrency & Race Condition Handling

Because Caffeine evicts entries asynchronously on background threads, a file could expire while a user is mid-export. To prevent FileNotFoundException mid-stream:
• Atomic Reference Counting: Wrap the Path in an ArtifactHandle containing an AtomicInteger activeReaders and an AtomicBoolean markedForDeletion.
• Read Flow:
	1. Caller acquires handle from Caffeine and increments activeReaders.
	2. If markedForDeletion is already true, abort and trigger a fresh report run.
	3. In a finally block after IRenderTask.render() finishes, decrement activeReaders. If activeReaders == 0 and markedForDeletion == true, delete the file immediately.
• Eviction Flow:
	1. RemovalListener sets markedForDeletion = true.
	2. If activeReaders == 0, delete the file immediately; otherwise, leave cleanup to the finishing reader thread.

5. Reference Implementation


5.1 Metadata Handle (ArtifactHandle.java)

java
public class ArtifactHandle {
    private final Path filePath;
    private final long sizeBytes;
    private final AtomicInteger activeReaders = new AtomicInteger(0);
    private final AtomicBoolean markedForDeletion = new AtomicBoolean(false);

    public ArtifactHandle(Path filePath, long sizeBytes) {
        this.filePath = filePath;
        this.sizeBytes = sizeBytes;
    }

    public boolean tryAcquire() {
        if (markedForDeletion.get()) return false;
        activeReaders.incrementAndGet();
        // Double-check after increment to close race window
        if (markedForDeletion.get()) {
            release();
            return false;
        }
        return true;
    }

    public void release() {
        if (activeReaders.decrementAndGet() == 0 && markedForDeletion.get()) {
            deleteQuietly();
        }
    }

    public void evict() {
        markedForDeletion.set(true);
        if (activeReaders.get() == 0) {
            deleteQuietly();
        }
    }

    private void deleteQuietly() {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {
            // Log warning for background sweep
        }
    }

    public Path getFilePath() { return filePath; }
    public long getSizeBytes() { return sizeBytes; }
}
Use code with caution.

5.2 Cache Configuration (BirtCacheConfig.java)

Uses weight-based eviction (capping total disk usage in bytes rather than arbitrary entry counts) so a sudden spike of massive reports cannot fill up the server's hard drive.
java
@Configuration
public class BirtCacheConfig {

    private final Path cacheDir = Paths.get(System.getProperty("java.io.tmpdir"), "birt-rpt-cache");

    // Cap total disk cache footprint at 10 GB (adjust based on disk volume)
    private static final long MAX_CACHE_DISK_BYTES = 10L * 1024 * 1024 * 1024;

    @PostConstruct
    public void init() throws IOException {
        if (Files.exists(cacheDir)) {
            FileSystemUtils.deleteRecursively(cacheDir);
        }
        Files.createDirectories(cacheDir);
    }

    @Bean
    public Cache<String, ArtifactHandle> rptDocumentCache(MeterRegistry meterRegistry) {
        Cache<String, ArtifactHandle> cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofHours(1))
                .maximumWeight(MAX_CACHE_DISK_BYTES)
                .weigher((String key, ArtifactHandle handle) -> 
                        (int) Math.min(handle.getSizeBytes() / 1024, Integer.MAX_VALUE)) // Weight in KB
                .removalListener((String key, ArtifactHandle handle, RemovalCause cause) -> {
                    if (handle != null) {
                        handle.evict();
                    }
                })
                .recordStats()
                .build();

        // Bind Caffeine hit/miss/eviction metrics directly to Micrometer
        CaffeineCacheMetrics.monitor(meterRegistry, cache, "birt_rptdocument_cache");
        return cache;
    }

    @PreDestroy
    public void shutdown() throws IOException {
        FileSystemUtils.deleteRecursively(cacheDir);
    }
}
Use code with caution.

6. Day-1 Telemetry Plan (Solving the Blind Spot)

Since you currently lack production telemetry, instrument the service from day one using Spring Boot Actuator + Micrometer so you can validate assumptions within the first week of traffic:

Metric Name	Type	Purpose
birt.rptdoc.size.bytes	DistributionSummary	Tracks p50, p95, and p99 .rptdocument file sizes to properly size MAX_CACHE_DISK_BYTES.
birt.run.duration	Timer	Measures database + report generation time saved per cache hit.
birt.render.duration	Timer	Measures disk/OS-page-cache read + export latency (PDF/XLSX).
birt_rptdocument_cache	CaffeineMetrics	Tracks cache hit ratio (gets), eviction count, and capacity pressure.


7. Operational & Sizing Guidelines

• JVM Heap Sizing: Because .rptdocument artifacts live on disk and BIRT streams data in chunks, keep your JVM heap modest (-Xms2g -Xmx4g) to maintain fast G1GC pause times.
• BIRT Data Engine Spillover: Configure DataEngine.MEMORY_BUFFER_SIZE (e.g., 10 to 25 MB per task) in your BIRT EngineConfig so large SQL result sets spill to temp disk rather than spiking JVM heap during IRunTask.
• Storage Volume: Point java.io.tmpdir (or a dedicated birt.cache.dir property) to a fast local NVMe/SSD partition—or /dev/shm on Linux if your server has abundant unused RAM and you want pure RAM-disk performance.
• Safety Cron / Fallback: Add a lightweight @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES) sweep that deletes any .rptdocument file older than 75 minutes on disk as a failsafe against edge-case file locks.
Would you like me to also write the BirtReportService implementation showing how to wire IReportEngine, IRunTask, IRenderTask, and the Micrometer metrics together with this ArtifactHandle pattern?
