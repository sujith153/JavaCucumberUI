package org.example.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.api.stepdefs", "org.example.api.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/api/cucumber-pretty.html",
        "json:target/cucumber-reports/api/cucumber.json"
    }
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}
