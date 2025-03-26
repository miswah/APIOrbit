package com.miswah.apiorbit.validators;

import jakarta.validation.ConstraintValidatorContext;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;

public class UrlPathValidator {

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy = UrlPathValidatorImpl.class)
    public @interface ValidUrlPath {
        String message() default "Invalid URL path";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    public static class UrlPathValidatorImpl implements ConstraintValidator<ValidUrlPath, String> {
        // Regex for valid URL path
        // Allows:
        // - Starts with /
        // - Contains only alphanumeric characters, -, _, ., /
        // - Consecutive / are not allowed
        // - Cannot end with .
        private static final Pattern URL_PATH_PATTERN = Pattern.compile("^/([a-zA-Z0-9]+[-_.]?[a-zA-Z0-9]+)*(/[a-zA-Z0-9]+[-_.]?[a-zA-Z0-9]+)*/?$");

        @Override
        public boolean isValid(String urlPath, ConstraintValidatorContext context) {
            // Check for null or empty
            if (urlPath == null || urlPath.trim().isEmpty()) {
                return false;
            }

            // Check against regex pattern
            return URL_PATH_PATTERN.matcher(urlPath).matches();
        }
    }
}
