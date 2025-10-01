package com.miswah.apiorbit.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miswah.apiorbit.dto.utils.ActivityLogEntry;
import com.miswah.apiorbit.model.ActivityLogModel;
import com.miswah.apiorbit.service.ActivityLogService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ActivityLogAspect {

    private final ActivityLogService logService;
    private final ObjectMapper objectMapper;
    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();


    public ActivityLogAspect(ActivityLogService logService, ObjectMapper objectMapper) {
        this.logService = logService;
        this.objectMapper = objectMapper;
    }

    //Enables auto log for all methods annotated with restcontroller
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void anyRestController() {
//        System.out.println("hello");
//    }

    @Pointcut("@annotation(activityLog)")
    public void annotatedWithActivityLog(ActivityLog activityLog) {}

    @Around("annotatedWithActivityLog(activityLog)")
    public Object around(ProceedingJoinPoint pjp, ActivityLog activityLog) throws Throwable {
        long start = System.currentTimeMillis();
        ActivityLogEntry entry = new ActivityLogEntry();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            entry.setUserId(auth.getName());
            //TODO: add user Name
        } else {
            entry.setUserId("anonymousUser");
        }

        // 2) get request info if present
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest req = attrs.getRequest();
            entry.setRemoteAddr(req.getHeader("X-Forwarded-For") != null ? req.getHeader("X-Forwarded-For") : req.getRemoteAddr());
            entry.setHttpMethod(req.getMethod());
            entry.setPath(req.getRequestURI());
        }

        entry.setModuleName(activityLog.module());
        entry.setActionName(activityLog.action());

        // 3) capture method params (mask sensitive)
        if (activityLog.captureParams()) {
            Object[] args = pjp.getArgs();
            String paramsJson = safeSerializeArgs(args);
            entry.setParams(paramsJson);
        }

        // 4) Target object
        try {
            Object result = pjp.proceed();
            long duration = (int)(System.currentTimeMillis() - start);
            entry.setDurationMs((int)duration);
            entry.setOutcome("SUCCESS");

            String targetSpel = activityLog.target(); // e.g. "#id" or "return?.id"
            if (!targetSpel.isEmpty()) {
                StandardEvaluationContext context = new StandardEvaluationContext();
                // add method arguments to context
                Object[] args = pjp.getArgs();
                String[] paramNames = nameDiscoverer.getParameterNames(((MethodSignature) pjp.getSignature()).getMethod());
                if (paramNames != null) {
                    for (int i = 0; i < paramNames.length; i++) {
                        context.setVariable(paramNames[i], args[i]);
                    }
                }
                // add the return value to context
                context.setVariable("result", result);

                try {
                    Object targetId = parser.parseExpression(targetSpel).getValue(context);
                    entry.setTargetId(targetId != null ? targetId.toString() : null);
                } catch (Exception e) {
                    log.warn("Failed to evaluate target SpEL: {}", targetSpel, e);
                }
            }

            persistEntry(entry);
            return result;
        } catch (Throwable ex) {
            long duration = (int)(System.currentTimeMillis() - start);
            entry.setDurationMs((int)duration);
            entry.setOutcome("EXCEPTION: " + ex.getClass().getSimpleName());
            entry.setParams(entry.getParams() + ""); // preserve params
            persistEntry(entry);
            throw ex;
        }
    }

    private void persistEntry(ActivityLogEntry entry) {
        ActivityLogModel entity = mapToEntity(entry);
        try {
            // Option A: synchronous save (simple + consistent)
            logService.save(entity);
            // Option B (alternative): publish event / async writer to a queue if you need lower latency
        } catch (Exception e) {
            // best-effort: do not block business flow; maybe fallback to File logging
            log.error("Failed to persist activity log", e);
        }
    }

    private String safeSerializeArgs(Object[] args) {
        try {
            // mask sensitive by inspecting param names or types (e.g., HttpServletRequest, Principal)
            // simplest: exclude HttpServletRequest/HttpServletResponse, and scrub fields named "password"
            return objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            return "[unserializable]";
        }
    }

    private ActivityLogModel mapToEntity(ActivityLogEntry entry){
        ActivityLogModel model = new ActivityLogModel();
        model.setUserId(entry.getUserId());
        model.setUserName(entry.getUserName());
        model.setDurationMs(entry.getDurationMs());
        model.setActionName(entry.getActionName());
        model.setRemoteAddr(entry.getRemoteAddr());
        model.setHttpMethod(entry.getHttpMethod());
        model.setPath(entry.getPath());
        model.setModuleName(entry.getModuleName());
        model.setTargetId(entry.getTargetId());
        model.setOutcome(entry.getOutcome());
        model.setParams(entry.getParams());
        model.setExtra(entry.getExtra());
        model.setCreatedBy(entry.getCreatedBy());
        return model;
    }
}
