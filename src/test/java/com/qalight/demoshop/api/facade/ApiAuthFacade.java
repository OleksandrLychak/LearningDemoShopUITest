package com.qalight.demoshop.api.facade;

import com.qalight.demoshop.api.client.ApiClient;
import com.qalight.demoshop.api.models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class ApiAuthFacade {

    private static final Logger LOG = LoggerFactory.getLogger(ApiAuthFacade.class);

    private final ApiClient apiClient;

    public ApiAuthFacade(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String registerAndVerifySessionData(String email, String password) {
        LOG.info("Facade: registering and verifying session data for {}", email);

        AuthResponse response = apiClient.register(email, password);

        Assert.assertTrue(response.isStatus(), "Facade: registration should succeed");
        Assert.assertNotNull(response.getUser(), "Facade: response must contain user data");
        Assert.assertTrue(response.getUser().getId() > 0,
                "Facade: user id must be a positive number");
        Assert.assertNotNull(response.getUser().getToken(),
                "Facade: response must contain a session token");
        Assert.assertFalse(response.getUser().getToken().isEmpty(),
                "Facade: session token must not be empty");

        LOG.info("Facade: session data verified for user id {}", response.getUser().getId());
        return response.getUser().getToken();
    }
}