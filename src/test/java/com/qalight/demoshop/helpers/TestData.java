package com.qalight.demoshop.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class TestData {

    private static final String EMAIL_PREFIX = "qauser";
    private static final String EMAIL_DOMAIN = "@sharkscode.com";
    private static final String DEFAULT_PASSWORD = "TestPass123!";
    private static final DateTimeFormatter TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private TestData() {

    }

    public static String generateUniqueEmail() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP);
        int counter = COUNTER.incrementAndGet();
        return EMAIL_PREFIX + "+" + timestamp + "_" + counter + EMAIL_DOMAIN;
    }

    public static String defaultPassword() {
        return DEFAULT_PASSWORD;
    }

    public static String generateFirstName() {
        return "TestFirst" + COUNTER.get();
    }

    public static String generateLastName() {
        return "TestLast" + COUNTER.get();
    }
}