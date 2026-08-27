package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.helpers.ApiTestData;
import com.qalight.demoshop.api.models.AuthResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationApiTest extends ApiBaseTest {

    @Test
    public void newUserCanRegisterAndReceiveSessionData() {
        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        AuthResponse response = getApiClient().register(email, password);

        Assert.assertTrue(response.isStatus(), "Registration should succeed");
        Assert.assertNotNull(response.getUser(), "Response must contain user data");
        Assert.assertTrue(response.getUser().getId() > 0, "User id must be a positive number");
        Assert.assertNotNull(response.getUser().getToken(), "Response must contain a session token");
        Assert.assertFalse(response.getUser().getToken().isEmpty(), "Session token must not be empty");
    }
}