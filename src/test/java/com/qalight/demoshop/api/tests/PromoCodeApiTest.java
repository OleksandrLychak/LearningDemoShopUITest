package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.helpers.ApiTestData;
import com.qalight.demoshop.api.models.AuthResponse;
import com.qalight.demoshop.api.models.PromoCodeResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PromoCodeApiTest extends ApiBaseTest {

    private static final String VALID_PROMO_CODE = "STAGTEST";

    @Test
    public void loggedInUserCanActivatePromoCode() {
        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        AuthResponse registerResponse = getApiClient().register(email, password);
        String token = registerResponse.getUser().getToken();
        Assert.assertNotNull(token, "Precondition: user must have a valid session token");

        PromoCodeResponse promoResponse = getApiClient().activatePromoCode(token, VALID_PROMO_CODE);

        Assert.assertTrue(promoResponse.isStatus(), "Promo code activation should succeed");
    }
}