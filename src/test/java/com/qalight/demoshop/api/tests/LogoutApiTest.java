package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.helpers.ApiTestData;
import com.qalight.demoshop.api.models.AuthResponse;
import com.qalight.demoshop.api.models.GuestResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Base64;

public class LogoutApiTest extends ApiBaseTest {

    @Test
    public void loggedInUserCanLogOut() {
        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        AuthResponse registerResponse = getApiClient().register(email, password);
        long loggedInUserId = registerResponse.getUser().getId();
        Assert.assertTrue(loggedInUserId > 0, "Precondition: user must be registered and logged in");

        GuestResponse guestResponse = getApiClient().getGuestSession();

        Assert.assertNotNull(guestResponse.getToken(), "Guest session must return a token");
        long guestUserId = extractUserIdFromToken(guestResponse.getToken());
        Assert.assertEquals(guestUserId, 0, "Guest token must belong to anonymous user (id 0)");
        Assert.assertNotEquals(
                guestResponse.getToken(),
                registerResponse.getUser().getToken(),
                "Guest token must be different from the previously logged-in user's token"
        );
    }

    private long extractUserIdFromToken(String jwt) {
        String[] parts = jwt.split("\\.");
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
        int idIndex = payloadJson.indexOf("\"id\":");
        String afterId = payloadJson.substring(idIndex + 5);
        String idValue = afterId.split("[,}]")[0].trim();
        return Long.parseLong(idValue);
    }
}