package com.qalight.demoshop.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.qalight.demoshop.config.ConfigReader;

public class DriverFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page startBrowser() {
        playwright = Playwright.create();
        browser = launchBrowser();
        context = browser.newContext(new Browser.NewContextOptions()
                .setBaseURL(ConfigReader.getBaseUrl()));
        context.setDefaultTimeout(ConfigReader.getDefaultTimeout());
        page = context.newPage();
        return page;
    }

    public void stopBrowser() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public Page getPage() {
        return page;
    }

    private Browser launchBrowser() {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.isHeadless());

        String browserName = ConfigReader.getBrowser().toLowerCase();
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