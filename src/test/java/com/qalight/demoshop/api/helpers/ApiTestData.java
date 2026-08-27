package com.qalight.demoshop.api.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class ApiTestData {

    private static final String BASE_EMAIL = "neville";
    private static final String DOMAIN = "@sharkscode.com";
    private static final DateTimeFormatter TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private static final String DEFAULT_PASSWORD = "1qaz@WSX";

    private ApiTestData() {
        // utility class, no instances
    }

    public static String generateUniqueEmail() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP);
        int counter = COUNTER.incrementAndGet();
        return BASE_EMAIL + "+" + timestamp + "_" + counter + DOMAIN;
    }

    public static String defaultPassword() {
        return DEFAULT_PASSWORD;
    }
}