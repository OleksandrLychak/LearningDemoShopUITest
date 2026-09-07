package com.qalight.demoshop.api.client;

import com.qalight.demoshop.api.models.AuthResponse;
import com.qalight.demoshop.api.models.GuestResponse;
import com.qalight.demoshop.api.models.PromoCodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingApiClientDecorator implements ApiClient {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingApiClientDecorator.class);

    private final ApiClient delegate;

    public LoggingApiClientDecorator(ApiClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public AuthResponse register(String email, String password) {
        LOG.info("Registering via API: {}", email);
        AuthResponse response = delegate.register(email, password);
        LOG.info("Register response: status={}, userId={}",
                response.isStatus(),
                response.getUser() != null ? response.getUser().getId() : "n/a");
        return response;
    }

    @Override
    public AuthResponse login(String email, String password) {
        LOG.info("Logging in via API: {}", email);
        AuthResponse response = delegate.login(email, password);
        LOG.info("Login response: status={}", response.isStatus());
        return response;
    }

    @Override
    public GuestResponse getGuestSession() {
        LOG.info("Requesting guest session (simulates logout)");
        GuestResponse response = delegate.getGuestSession();
        LOG.info("Guest session token received");
        return response;
    }

    @Override
    public PromoCodeResponse activatePromoCode(String token, String code) {
        LOG.info("Activating promo code: {}", code);
        PromoCodeResponse response = delegate.activatePromoCode(token, code);
        LOG.info("Promo code activation response: status={}", response.isStatus());
        return response;
    }
}