package com.qalight.demoshop.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfigReader {

    private static final String CONFIG_FILE = "api.config.properties";
    private static final Properties properties = loadProperties();

    private ApiConfigReader() {
        // utility class, no instances
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = ApiConfigReader.class
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
        return properties.getProperty("api.base.url");
    }

    public static double getDefaultTimeout() {
        return Double.parseDouble(properties.getProperty("api.default.timeout", "10000"));
    }
}