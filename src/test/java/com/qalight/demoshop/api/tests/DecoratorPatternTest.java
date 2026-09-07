package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.client.ApiClient;
import com.qalight.demoshop.api.client.LoggingApiClientDecorator;
import com.qalight.demoshop.api.client.PlainSlotCityApiClient;
import com.qalight.demoshop.api.helpers.ApiTestData;
import com.qalight.demoshop.api.models.AuthResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DecoratorPatternTest extends ApiBaseTest {

    @Test
    public void plainClientWorksWithoutDecorator() {
        ApiClient plainClient = new PlainSlotCityApiClient(getApiRequestContext());

        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        AuthResponse response = plainClient.register(email, password);

        Assert.assertTrue(response.isStatus(), "Plain client should register successfully");
    }

    @Test
    public void decoratedClientProducesTheSameResultAsPlainClient() {
        ApiClient plainClient = new PlainSlotCityApiClient(getApiRequestContext());
        ApiClient decoratedClient = new LoggingApiClientDecorator(plainClient);

        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        AuthResponse response = decoratedClient.register(email, password);

        Assert.assertTrue(response.isStatus(),
                "Decorated client must produce the same successful result as plain client, "
                        + "just with added logging around the call");
        Assert.assertTrue(response.getUser().getId() > 0);
    }
}