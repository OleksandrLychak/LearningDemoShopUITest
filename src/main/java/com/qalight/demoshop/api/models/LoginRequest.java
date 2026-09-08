package com.qalight.demoshop.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString(exclude = "password")
public class LoginRequest {

    @Builder.Default
    private final String type = "email";
    private final String email;
    private final String password;
    @Builder.Default
    private final Device device = Device.testDevice();
}