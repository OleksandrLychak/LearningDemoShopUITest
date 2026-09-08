package com.qalight.demoshop.api.tests;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.qalight.demoshop.api.client.ApiClient;
import com.qalight.demoshop.api.client.PlainSlotCityApiClient;
import com.qalight.demoshop.api.client.decorator.LoggingApiClientDecorator;
import com.qalight.demoshop.api.config.ApiConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class ApiBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApiBaseTest.class);

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private ApiClient apiClient;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        LOG.info("Warming up Cloudflare trust via real browser navigation");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));

        context = browser.newContext(new Browser.NewContextOptions()
                .setBaseURL(ApiConfigReader.getBaseUrl()));

        Page warmUpPage = context.newPage();
        warmUpPage.navigate(ApiConfigReader.getBaseUrl());
        warmUpPage.close();

        APIRequestContext request = context.request();
        apiClient = new LoggingApiClientDecorator(new PlainSlotCityApiClient(request));
        LOG.info("API client ready");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

        sleepBriefly();
    }

    private void sleepBriefly() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected ApiClient getApiClient() {
        return apiClient;
    }

    protected APIRequestContext getApiRequestContext() {
        return context.request();
    }
}