package com.qalight.demoshop.helpers;

import com.qalight.demoshop.models.User;

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
        // utility class, no instances
    }

    public static User generateUser() {
        int counter = COUNTER.incrementAndGet();
        String timestamp = LocalDateTime.now().format(TIMESTAMP);
        String email = EMAIL_PREFIX + "+" + timestamp + "_" + counter + EMAIL_DOMAIN;

        return new User(
                "TestFirst" + counter,
                "TestLast" + counter,
                email,
                DEFAULT_PASSWORD,
                User.Gender.MALE
        );
    }

    @Deprecated
    public static String generateUniqueEmail() {
        return generateUser().getEmail();
    }

    public static String defaultPassword() {
        return DEFAULT_PASSWORD;
    }
}