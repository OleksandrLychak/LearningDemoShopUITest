package com.qalight.demoshop.api.models;

public class RegisterRequest {

    private final String type = "email";
    private final String password;
    private final String email;
    private final String promokey = "";
    private final String ref_code = "";
    private final int is_accept = 1;
    private final Device device;
    private final String language = "uk";

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.device = Device.testDevice();
    }
}