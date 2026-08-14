package com.qalight.demoshop.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = loadProperties();

    private ConfigReader() {
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (input == null) {
                throw new IllegalStateException(
                        "Config file not found in classpath: " + CONFIG_FILE);
            }
            props.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load config file: " + CONFIG_FILE, e);
        }
        return props;
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chromium");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "true"));
    }

    public static int getDefaultTimeout() {
        return Integer.parseInt(properties.getProperty("default.timeout", "10000"));
    }

    public static String getScreenshotsDir() {
        return properties.getProperty("screenshots.dir", "build/screenshots");
    }
}