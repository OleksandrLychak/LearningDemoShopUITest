package com.qalight.demoshop.api.helpers;

import com.qalight.demoshop.api.models.LoginRequest;
import com.qalight.demoshop.api.models.PromoCodeRequest;

public final class ApiTestDataBuilders {

    private ApiTestDataBuilders() {
        // utility class, no instances
    }

    public static PromoCodeRequest validPromoCodeRequest() {
        return PromoCodeRequest.builder()
                .code("STAGTEST")
                .autoApply(true)
                .build();
    }

    public static PromoCodeRequest promoCodeRequestMissingCode() {
        return PromoCodeRequest.builder()
                .autoApply(true)
                .build();
    }

    public static LoginRequest validLoginRequest(String email, String password) {
        return LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}