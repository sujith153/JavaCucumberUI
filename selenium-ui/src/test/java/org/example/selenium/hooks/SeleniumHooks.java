package org.example.selenium.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.core.config.TestConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumHooks {
    private static final Logger log = LoggerFactory.getLogger(SeleniumHooks.class);
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting scenario: {} using Selenium", scenario.getName());

        String browser = TestConfig.getInstance().getProperty("selenium.browser", "chrome");

        // Setup WebDriver based on configuration
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        // Configure browser window
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && driver != null) {
                log.error("Scenario failed: {}", scenario.getName());
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot-" + scenario.getName());
            }
        } catch (Exception e) {
            log.error("Failed to capture screenshot: {}", e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
            log.info("Completed scenario: {} with status: {}", scenario.getName(), scenario.getStatus());
        }
    }
}
