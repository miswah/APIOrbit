package com.miswah.apiorbit.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(Loggable)")
    public void loggableMethods() {
    }

    @Before("loggableMethods()")
    public void logBefore(JoinPoint joinPoint) {
        CustomLogger.logInfo(LoggingAspect.class, "Logging before " + joinPoint.getSignature().toShortString() + "Body: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        CustomLogger.logInfo(LoggingAspect.class, "Logging after " + joinPoint.getSignature().toShortString() + ". Result: " + result);
    }
}