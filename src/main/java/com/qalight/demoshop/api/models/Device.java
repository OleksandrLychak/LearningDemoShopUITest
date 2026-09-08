package com.qalight.demoshop.api.models;

public class Device {

    private final String platform;
    private final String deviceId;
    private final String deviceModel;
    private final String osVersion;
    private final String browserName;
    private final String browserVersion;
    private final String userAgent;

    public Device(String platform, String deviceId, String deviceModel,
                  String osVersion, String browserName, String browserVersion,
                  String userAgent) {
        this.platform = platform;
        this.deviceId = deviceId;
        this.deviceModel = deviceModel;
        this.osVersion = osVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.userAgent = userAgent;
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