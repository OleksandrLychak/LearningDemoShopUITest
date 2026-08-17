package com.qalight.demoshop.utils;

import java.util.regex.Pattern;

public final class UrlPatterns {

    // URLs
    public static final Pattern LOGIN_PAGE = Pattern.compile(".*/login.*");
    public static final Pattern REGISTER_RESULT_PAGE = Pattern.compile(".*/registerresult.*");
    public static final Pattern SEARCH_PAGE = Pattern.compile(".*/search.*");

    // Titles
    public static final Pattern SEARCH_TITLE = Pattern.compile(".*Search.*");

    private UrlPatterns() {
        // utility class, no instances
    }
}