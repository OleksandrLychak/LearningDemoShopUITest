package com.qalight.demoshop.config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppConfigTest {

    @Test
    public void getInstanceAlwaysReturnsTheSameObject() {
        AppConfig first = AppConfig.getInstance();
        AppConfig second = AppConfig.getInstance();

        Assert.assertSame(first, second,
                "getInstance() must return the same object reference every time");
    }

    @Test
    public void singletonInstanceReadsRealConfigValues() {
        AppConfig config = AppConfig.getInstance();

        Assert.assertNotNull(config.getBaseUrl(),
                "Singleton instance must load real values from config.properties");
    }
}