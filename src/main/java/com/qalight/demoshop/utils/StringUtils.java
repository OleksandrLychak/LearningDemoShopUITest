package com.qalight.demoshop.utils;

public final class StringUtils {

    private StringUtils() {
        // utility class, no instances
    }

    public static String joinSegments(String... segments) {
        return "/" + String.join("/", segments);
    }
}