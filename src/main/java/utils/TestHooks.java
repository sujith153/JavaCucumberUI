package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.codeborne.selenide.Selenide;
import config.FrameworkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestHooks {
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {} at {}",
            scenario.getName(),
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );

        // Setup framework configuration
        FrameworkConfig.setupConfig();

        // Clear cookies and local storage before each scenario
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

        // Set retry count if specified
        if (PropertyReader.getProperty("retry.count") != null) {
            int retryCount = PropertyReader.getIntProperty("retry.count");
            logger.info("Setting retry count to: {}", retryCount);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario failed: {}", scenario.getName());
                String screenshotName = String.format("failure_%s_%s",
                    scenario.getName().replaceAll("\\s+", "_"),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                );
                Selenide.screenshot(screenshotName);
                logger.info("Screenshot captured: {}", screenshotName);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        } finally {
            // Clean up resources
            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
            Selenide.closeWebDriver();
            logger.info("Completed scenario: {} with status: {}",
                scenario.getName(),
                scenario.getStatus()
            );
        }
    }
}
