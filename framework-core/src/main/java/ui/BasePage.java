package org.example.core.ui;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Slf4j
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementVisible(By locator) {
        log.debug("Waiting for element to be visible: {}", locator);
        wait.until(d -> d.findElement(locator).isDisplayed());
    }

    protected void waitForPageLoad() {
        log.debug("Waiting for page to load");
        wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
            .executeScript("return document.readyState").equals("complete"));
    }
}
