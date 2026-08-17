package com.qalight.demoshop.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.qalight.demoshop.config.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

    public Page startBrowser() {
        LOG.info("Starting Playwright driver");
        playwright = Playwright.create();
        browser = launchBrowser();
        LOG.info("Creating new browser context with baseURL={}", ConfigReader.getBaseUrl());
        context = browser.newContext(new Browser.NewContextOptions()
                .setBaseURL(ConfigReader.getBaseUrl()));
        context.setDefaultTimeout(ConfigReader.getDefaultTimeout());
        page = context.newPage();
        LOG.info("Browser is ready");
        return page;
    }

    public void stopBrowser() {
        LOG.info("Stopping browser and cleaning up Playwright resources");
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public Page getPage() {
        return page;
    }

    private Browser launchBrowser() {
        String browserName = ConfigReader.getBrowser().toLowerCase();
        boolean headless = ConfigReader.isHeadless();
        LOG.info("Launching {} in headless={} mode", browserName, headless);
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        return switch (browserName) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            case "chromium" -> playwright.chromium().launch(options);
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browserName +
                            ". Use chromium, firefox or webkit.");
        };
    }
}