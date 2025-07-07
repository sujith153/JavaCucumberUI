package org.example.selenide;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"org.example.selenide.stepdefs", "org.example.selenide.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/selenide-ui/cucumber-pretty.html",
        "json:target/cucumber-reports/selenide-ui/cucumber.json",
        "testng:target/cucumber-reports/selenide-ui/cucumber-testng.xml",
        "rerun:target/cucumber-reports/selenide-ui/failed_scenarios.txt"
    },
    monochrome = true,
    publish = true
)
public class SelenideIT extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
