package com.qalight.demoshop.api.client;

import com.google.gson.Gson;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qalight.demoshop.api.models.AuthResponse;
import com.qalight.demoshop.api.models.GuestResponse;
import com.qalight.demoshop.api.models.LoginRequest;
import com.qalight.demoshop.api.models.PromoCodeResponse;
import com.qalight.demoshop.api.models.RegisterRequest;

public class PlainSlotCityApiClient implements ApiClient {

    private static final Gson GSON = new Gson();

    private final APIRequestContext request;

    public PlainSlotCityApiClient(APIRequestContext request) {
        this.request = request;
    }

    @Override
    public AuthResponse register(String email, String password) {
        RegisterRequest requestBody = new RegisterRequest(email, password);
        APIResponse response = request.post(
                ApiEndpoints.register(),
                RequestOptions.create()
                        .setHeader(ApiHeader.CONTENT_TYPE.headerName(), "application/json")
                        .setData(GSON.toJson(requestBody))
        );
        return parse(response, AuthResponse.class);
    }

    @Override
    public AuthResponse login(String email, String password) {
        LoginRequest requestBody = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
        APIResponse response = request.post(
                ApiEndpoints.login(),
                RequestOptions.create()
                        .setHeader(ApiHeader.CONTENT_TYPE.headerName(), "application/json")
                        .setData(GSON.toJson(requestBody))
        );
        return parse(response, AuthResponse.class);
    }

    @Override
    public GuestResponse getGuestSession() {
        APIResponse response = request.get(ApiEndpoints.guestSession());
        return parse(response, GuestResponse.class);
    }

    @Override
    public PromoCodeResponse activatePromoCode(String token, String code) {
        APIResponse response = request.post(
                ApiEndpoints.activatePromoCode(),
                RequestOptions.create()
                        .setHeader(ApiHeader.CONTENT_TYPE.headerName(), "application/json")
                        .setHeader(ApiHeader.AUTHORIZATION.headerName(), "Bearer " + token)
                        .setData("{\"code\":\"" + code + "\"}")
        );
        return parse(response, PromoCodeResponse.class);
    }

    private <T> T parse(APIResponse response, Class<T> type) {
        String bodyText = response.text();
        if (response.status() >= 500) {
            throw new IllegalStateException(
                    "Server returned " + response.status() + " (likely Cloudflare "
                            + "rate limiting or a challenge page) instead of a valid API response. "
                            + "Body: " + bodyText.substring(0, Math.min(200, bodyText.length())));
        }
        return GSON.fromJson(bodyText, type);
    }
}