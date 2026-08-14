package com.qalight.demoshop.tests;

import com.microsoft.playwright.Page;
import com.qalight.demoshop.driver.DriverFactory;
import com.qalight.demoshop.driver.ScreenshotListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotListener.class)
public abstract class BaseTest {

    private DriverFactory driverFactory;
    private Page page;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driverFactory = new DriverFactory();
        page = driverFactory.startBrowser();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driverFactory != null) {
            driverFactory.stopBrowser();
        }
    }

    public Page getPage() {
        return page;
    }
}