package org.example.selenide.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelenideHooks {
    private static final Logger log = LoggerFactory.getLogger(SelenideHooks.class);

    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting scenario: {}", scenario.getName());

        // Configure Selenide settings
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.google.com";
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.headless = false;
        Configuration.reportsFolder = "target/selenide-reports";

        // Clear browser state if browser is already started
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && WebDriverRunner.hasWebDriverStarted()) {
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWindow();
        }
    }
}
