package com.qalight.demoshop.tests;

import com.qalight.demoshop.pages.HomePage;
import com.microsoft.playwright.Page;
import com.qalight.demoshop.driver.DriverFactory;
import com.qalight.demoshop.driver.ScreenshotListener;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listeners(ScreenshotListener.class)
public abstract class BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    private DriverFactory driverFactory;
    private Page page;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        LOG.info("=== Starting test: {} ===", method.getName());
        driverFactory = new DriverFactory();
        page = driverFactory.startBrowser();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) {
        LOG.info("=== Finished test: {} ===", method.getName());
        if (driverFactory != null) {
            driverFactory.stopBrowser();
        }
    }

    public Page getPage() {
        return page;
    }

    protected HomePage openHomePage() {
        return new HomePage(getPage()).open();
    }
}