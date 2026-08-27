package com.qalight.demoshop.api.tests;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Playwright;
import com.qalight.demoshop.api.client.SlotCityApiClient;
import com.qalight.demoshop.api.config.ApiConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.microsoft.playwright.Page;

public abstract class ApiBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ApiBaseTest.class);

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private SlotCityApiClient apiClient;

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
        apiClient = new SlotCityApiClient(request);
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

    protected SlotCityApiClient getApiClient() {
        return apiClient;
    }
}