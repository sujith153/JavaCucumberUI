package org.example.selenide;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.selenide.stepdefs", "org.example.selenide.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/selenide-ui/cucumber-pretty.html",
        "json:target/cucumber-reports/selenide-ui/cucumber.json"
    }
)
public class SelenideTestRunner extends AbstractTestNGCucumberTests {
}
