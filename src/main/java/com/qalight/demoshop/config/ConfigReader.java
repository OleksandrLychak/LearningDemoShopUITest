package com.qalight.demoshop.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String BASE_FILE = "config.properties";
    private static final String LOCAL_FILE = "config.local.properties";
    private static final Properties properties = loadProperties();

    private ConfigReader() {
        // utility class, no instances
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        loadFromClasspath(props, BASE_FILE, true);
        loadFromClasspath(props, LOCAL_FILE, false);
        return props;
    }

    private static void loadFromClasspath(Properties target,
                                          String fileName,
                                          boolean required) {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (input == null) {
                if (required) {
                    throw new IllegalStateException(
                            "Required config file not found in classpath: " + fileName);
                }
                return;
            }
            target.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load config file: " + fileName, e);
        }
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

    public static String getTestUserEmail() {
        return properties.getProperty("test.user.email");
    }

    public static String getTestUserPassword() {
        return properties.getProperty("test.user.password");
    }
}