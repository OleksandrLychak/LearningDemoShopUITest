package com.qalight.demoshop.api.client;

public enum ApiHeader {

    CONTENT_TYPE("Content-Type"),
    AUTHORIZATION("Authorization");

    private final String headerName;

    ApiHeader(String headerName) {
        this.headerName = headerName;
    }

    public String headerName() {
        return headerName;
    }
}