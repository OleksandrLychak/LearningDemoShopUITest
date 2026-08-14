package com.qalight.demoshop.driver;

import com.microsoft.playwright.Page;
import com.qalight.demoshop.config.ConfigReader;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {

    private static final DateTimeFormatter TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Override
    public void onTestFailure(ITestResult result) {
        Page page = extractPage(result);
        if (page == null) {
            System.out.println("ScreenshotListener: no Page available, skipping screenshot");
            return;
        }

        String screenshotPath = buildScreenshotPath(result);

        try {
            Files.createDirectories(Paths.get(ConfigReader.getScreenshotsDir()));
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("ScreenshotListener: failed to save screenshot - " + e.getMessage());
        }
    }

    private Page extractPage(ITestResult result) {
        Object testInstance = result.getInstance();
        try {
            return (Page) testInstance.getClass()
                    .getMethod("getPage")
                    .invoke(testInstance);
        } catch (Exception e) {
            return null;
        }
    }

    private String buildScreenshotPath(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String timestamp = LocalDateTime.now().format(TIMESTAMP);
        String filename = className + "_" + methodName + "_" + timestamp + ".png";
        return Paths.get(ConfigReader.getScreenshotsDir(), filename).toString();
    }
}