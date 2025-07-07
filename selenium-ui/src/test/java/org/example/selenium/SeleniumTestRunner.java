package org.example.selenium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.selenium.stepdefs", "org.example.selenium.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/selenium-ui/cucumber-pretty.html",
        "json:target/cucumber-reports/selenium-ui/cucumber.json"
    }
)
public class SeleniumTestRunner extends AbstractTestNGCucumberTests {
}
