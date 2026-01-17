package com.miswah.apiorbit.dto.response;

public record SystemKPIResponseDTO(Long totalApis, Long activeApis, Long pendingApis, Long deprecatedApis, Long activeMocks, Long totalUsers) {
}
