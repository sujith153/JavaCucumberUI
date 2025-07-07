package config;

 import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TestConfig {
    private static final Properties properties = new Properties();

    private TestConfig() {
        // private constructor to enforce singleton
    }

    private static class Holder {
        private static final TestConfig INSTANCE = new TestConfig();

        static {
            loadProperties();
        }

        private static void loadProperties() {
            String env = System.getProperty("env", "qa");
            String configFile = String.format("config/%s/config.properties", env);

            try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream(configFile)) {
                if (input != null) {
                    properties.load(input);
                    log.info("Loaded configuration for environment: {}", env);
                } else {
                    log.warn("Config file not found: {}. Loading default configuration.", configFile);
                    try (InputStream defaultInput = TestConfig.class.getClassLoader()
                            .getResourceAsStream("config/base-config.properties")) {
                        if (defaultInput != null) {
                            properties.load(defaultInput);
                            log.info("Loaded default configuration");
                        }
                    }
                }
            } catch (IOException e) {
                log.warn("Failed to load configuration file", e);
            }
        }
    }

    public static TestConfig getInstance() {
        return Holder.INSTANCE;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }
}
