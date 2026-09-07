package com.qalight.demoshop.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static AppConfig instance;

    private final Properties properties;

    private AppConfig() {
        this.properties = loadProperties();
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = AppConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "Config file not found in classpath: config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load config file: config.properties", e);
        }
        return props;
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chromium");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "true"));
    }
}