package com.qalight.demoshop.api.client;

import com.qalight.demoshop.api.models.AuthResponse;
import com.qalight.demoshop.api.models.GuestResponse;
import com.qalight.demoshop.api.models.PromoCodeResponse;

public interface ApiClient {

    AuthResponse register(String email, String password);

    AuthResponse login(String email, String password);

    GuestResponse getGuestSession();

    PromoCodeResponse activatePromoCode(String token, String code);
}