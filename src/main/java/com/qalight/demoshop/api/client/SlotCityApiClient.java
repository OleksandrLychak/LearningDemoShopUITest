package com.qalight.demoshop.api.client;

import com.google.gson.Gson;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qalight.demoshop.api.models.AuthResponse;
import com.qalight.demoshop.api.models.GuestResponse;
import com.qalight.demoshop.api.models.PromoCodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlotCityApiClient {

    private static final Logger LOG = LoggerFactory.getLogger(SlotCityApiClient.class);
    private static final Gson GSON = new Gson();

    private final APIRequestContext request;

    public SlotCityApiClient(APIRequestContext request) {
        this.request = request;
    }

    public AuthResponse register(String email, String password) {
        LOG.info("Registering via API: {}", email);
        String body = registerPayload(email, password);

        APIResponse response = request.post(
                "/auth/v2/register?on_device=true",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(body)
        );

        return parse(response, AuthResponse.class);
    }

    public AuthResponse login(String email, String password) {
        LOG.info("Logging in via API: {}", email);
        String body = loginPayload(email, password);

        APIResponse response = request.post(
                "/auth/login?on_device=true",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(body)
        );

        return parse(response, AuthResponse.class);
    }

    public GuestResponse getGuestSession() {
        LOG.info("Requesting guest session (simulates logout)");

        APIResponse response = request.get("/auth/guest");

        return parse(response, GuestResponse.class);
    }

    public PromoCodeResponse activatePromoCode(String token, String code) {
        LOG.info("Activating promo code: {}", code);

        APIResponse response = request.post(
                "/apiv2/promocodes/activate",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer " + token)
                        .setData("{\"code\":\"" + code + "\"}")
        );

        return parse(response, PromoCodeResponse.class);
    }

    private <T> T parse(APIResponse response, Class<T> type) {
        String bodyText = response.text();
        LOG.info("Response [{}]: {}", response.status(), bodyText);

        if (response.status() >= 500) {
            throw new IllegalStateException(
                    "Server returned " + response.status() + " (likely Cloudflare "
                            + "rate limiting or a challenge page) instead of a valid API response. "
                            + "Body: " + bodyText.substring(0, Math.min(200, bodyText.length())));
        }

        return GSON.fromJson(bodyText, type);
    }

    private String registerPayload(String email, String password) {
        return String.format("""
                {
                  "type": "email",
                  "password": "%s",
                  "email": "%s",
                  "promokey": "",
                  "ref_code": "",
                  "is_accept": 1,
                  "device": {
                    "platform": "WEB",
                    "device_id": "test-device-id",
                    "device_model": "Web Test",
                    "os_version": "Test",
                    "browser_name": "Test",
                    "browser_version": "1.0",
                    "user_agent": "Playwright API Test"
                  },
                  "language": "uk"
                }
                """, password, email);
    }

    private String loginPayload(String email, String password) {
        return String.format("""
                {
                  "type": "email",
                  "password": "%s",
                  "email": "%s",
                  "device": {
                    "platform": "WEB",
                    "device_id": "test-device-id",
                    "device_model": "Web Test",
                    "os_version": "Test",
                    "browser_name": "Test",
                    "browser_version": "1.0",
                    "user_agent": "Playwright API Test"
                  }
                }
                """, password, email);
    }
}