package com.qalight.demoshop.api.models;

public class Device {

    private final String platform;
    private final String device_id;
    private final String device_model;
    private final String os_version;
    private final String browser_name;
    private final String browser_version;
    private final String user_agent;

    public Device(String platform, String deviceId, String deviceModel,
                  String osVersion, String browserName, String browserVersion,
                  String userAgent) {
        this.platform = platform;
        this.device_id = deviceId;
        this.device_model = deviceModel;
        this.os_version = osVersion;
        this.browser_name = browserName;
        this.browser_version = browserVersion;
        this.user_agent = userAgent;
    }

    public static Device testDevice() {
        return new Device(
                "WEB",
                "test-device-id",
                "Web Test",
                "Test",
                "Test",
                "1.0",
                "Playwright API Test"
        );
    }
}