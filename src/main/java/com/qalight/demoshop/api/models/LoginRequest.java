package com.qalight.demoshop.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString(exclude = "password")
public class LoginRequest {

    private final String email;
    private final String password;
    private final String deviceId;

    public String toJson() {
        return String.format(
                "{\"type\":\"email\",\"email\":\"%s\",\"password\":\"%s\",\"device\":{\"device_id\":\"%s\"}}",
                email, password, deviceId);
    }
}