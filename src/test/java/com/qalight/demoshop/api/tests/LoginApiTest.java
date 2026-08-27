package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.helpers.ApiTestData;
import com.qalight.demoshop.api.models.AuthResponse;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginApiTest extends ApiBaseTest {

    @Test
    public void createdUserCanLogIn() {
        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        AuthResponse registerResponse = getApiClient().register(email, password);
        long registeredUserId = registerResponse.getUser().getId();

        AuthResponse loginResponse = getApiClient().login(email, password);

        Assert.assertTrue(loginResponse.isStatus(), "Login should succeed");
        Assert.assertNotNull(loginResponse.getUser(), "Response must contain user data");
        Assert.assertEquals(
                loginResponse.getUser().getId(),
                registeredUserId,
                "Login must return the same user id as registration"
        );
        Assert.assertNotNull(loginResponse.getUser().getToken(), "Response must contain a session token");
        Assert.assertNotEquals(
                loginResponse.getUser().getToken(),
                registerResponse.getUser().getToken(),
                "Login should issue a fresh token, not reuse the registration one"
        );
    }
}