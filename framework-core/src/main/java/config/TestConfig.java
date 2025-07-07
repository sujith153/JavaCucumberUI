package org.example.core.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Getter
public class TestConfig {
    private static final Properties properties = new Properties();
    private static TestConfig instance;

    private TestConfig() {
        loadProperties();
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
        }
        return instance;
    }

    private void loadProperties() {
        String env = System.getProperty("env", "qa");
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config/" + env + "/config.properties"));
            log.info("Loaded configuration for environment: {}", env);
        } catch (IOException e) {
            log.error("Failed to load properties file", e);
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
