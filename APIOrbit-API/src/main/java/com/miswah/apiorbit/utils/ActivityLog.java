package com.miswah.apiorbit.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityLog {
    String action() default "";
    String module() default "";
    /**
     * optional: an SpEL expression to extract "targetId" from method args or return value
     * e.g. "#id" or "return?.id"
     */
    String target() default "";
    boolean captureParams() default true;
}
