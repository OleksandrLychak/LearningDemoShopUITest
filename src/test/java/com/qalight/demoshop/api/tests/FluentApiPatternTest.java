package com.qalight.demoshop.api.tests;

import com.microsoft.playwright.APIResponse;
import com.qalight.demoshop.api.client.ApiRequestBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FluentApiPatternTest extends ApiBaseTest {

    @Test
    public void promoCodeCanBeActivatedViaFluentRequestBuilder() {
        String email = com.qalight.demoshop.api.helpers.ApiTestData.generateUniqueEmail();
        String password = com.qalight.demoshop.api.helpers.ApiTestData.defaultPassword();

        String token = getApiClient().register(email, password).getUser().getToken();

        APIResponse response = new ApiRequestBuilder(getApiRequestContext())
                .withJsonContentType()
                .withHeader("Authorization", "Bearer " + token)
                .withBody("{\"code\":\"STAGTEST\"}")
                .post("/apiv2/promocodes/activate");

        Assert.assertEquals(response.status(), 200,
                "Fluent-built request should succeed with status 200");
    }
}