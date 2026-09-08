package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.models.LoginRequest;
import com.qalight.demoshop.api.models.PromoCodeRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuilderPatternTest {

    @Test
    public void manualBuilderProducesCorrectRequest() {
        PromoCodeRequest request = PromoCodeRequest.builder()
                .code("STAGTEST")
                .autoApply(true)
                .build();

        Assert.assertEquals(request.getCode(), "STAGTEST");
        Assert.assertEquals(request.getSource(), "web",
                "Default source should apply when not explicitly set");
        Assert.assertTrue(request.isAutoApply());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void manualBuilderRejectsMissingCode() {
        PromoCodeRequest.builder()
                .autoApply(true)
                .build();
    }

    @Test
    public void lombokBuilderProducesCorrectRequest() {
        LoginRequest request = LoginRequest.builder()
                .email("test@sharkscode.com")
                .password("secret123")
                .build();

        Assert.assertEquals(request.getEmail(), "test@sharkscode.com");
        Assert.assertNotNull(request.getDevice(),
                "Device should default via @Builder.Default when not explicitly set");
    }

    @Test
    public void lombokGeneratedToStringExcludesPassword() {
        LoginRequest request = LoginRequest.builder()
                .email("test@sharkscode.com")
                .password("secret123")
                .build();

        String result = request.toString();

        Assert.assertFalse(result.contains("secret123"),
                "toString() must never expose the password");
    }
}