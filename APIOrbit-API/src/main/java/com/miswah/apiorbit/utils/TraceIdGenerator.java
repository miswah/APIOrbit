package com.miswah.apiorbit.utils;

import java.util.Random;

public class TraceIdGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateTraceId() {
        StringBuilder traceId = new StringBuilder();
        Random random = new Random();

        // Add 3 random letters
        for (int i = 0; i < 3; i++) {
            traceId.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }

        // Add 16 random digits
        for (int i = 0; i < 16; i++) {
            traceId.append(random.nextInt(10));
        }

        return traceId.toString();
    }
}
