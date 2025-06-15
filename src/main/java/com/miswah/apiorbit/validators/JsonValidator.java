package com.miswah.apiorbit.validators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;


public class JsonValidator {

    @Documented
    @Constraint(validatedBy = JsonValidatorImpl.class)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidJson {
        String message() default "Invalid JSON format";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class JsonValidatorImpl implements ConstraintValidator<ValidJson, String> {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void initialize(ValidJson constraintAnnotation) {
            // Initialization if needed
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }

            try {
                objectMapper.readTree(value);
                return true;
            } catch (JsonProcessingException e) {
                return false;
            }
        }
    }
}
