package org.example.selenide.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.core.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelenideHooks {
    private static final Logger log = LoggerFactory.getLogger(SelenideHooks.class);

    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting scenario: {} using Selenide", scenario.getName());

        // Configure Selenide settings
        Configuration.browser = TestConfig.getInstance().getProperty("selenide.browser", "chrome");
        Configuration.timeout = Long.parseLong(TestConfig.getInstance().getProperty("selenide.timeout", "10000"));
        Configuration.headless = Boolean.parseBoolean(TestConfig.getInstance().getProperty("selenide.headless", "false"));
        Configuration.reportsFolder = "target/selenide-screenshots";

        // Clear browser state
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("Scenario failed: {}", scenario.getName());
            Selenide.screenshot("failure_" + scenario.getName());
        }

        // Cleanup
        Selenide.closeWebDriver();
        log.info("Completed scenario: {} with status: {}", scenario.getName(), scenario.getStatus());
    }
}
