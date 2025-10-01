package com.miswah.apiorbit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class CustomLogger {
    public static void logInfo(Class<?> clazz, String message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];

        logger.info("[TraceID: {}] [Class: {}] [Method: {}] [Line: {}] - {}",
                MDC.get("traceId"),
                clazz.getSimpleName(),
                caller.getMethodName(),
                caller.getLineNumber(),
                message);
    }

    public static void logError(Class<?> clazz, String message, Throwable e) {
        Logger logger = LoggerFactory.getLogger(clazz);
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];

        logger.error("[TraceID: {}] [Class: {}] [Method: {}] [Line: {}] - {}",
                MDC.get("traceId"),
                clazz.getSimpleName(),
                caller.getMethodName(),
                caller.getLineNumber(),
                message, e);
    }
}
