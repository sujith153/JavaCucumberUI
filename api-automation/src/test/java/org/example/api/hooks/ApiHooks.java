package org.example.api.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.core.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiHooks {
    private static final Logger log = LoggerFactory.getLogger(ApiHooks.class);

    @Before
    public void setUp(Scenario scenario) {
        log.info("Starting API scenario: {}", scenario.getName());

        // Configure REST Assured
        RestAssured.baseURI = TestConfig.getInstance().getProperty("api.base.url");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        // Set default timeouts
        RestAssured.config()
            .httpClient(httpClientConfig ->
                httpClientConfig
                    .setConnectTimeout(Integer.parseInt(TestConfig.getInstance().getProperty("api.timeout.connect", "10000")))
                    .setSocketTimeout(Integer.parseInt(TestConfig.getInstance().getProperty("api.timeout.socket", "10000")))
            );
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("Completed API scenario: {} with status: {}", scenario.getName(), scenario.getStatus());

        // Reset REST Assured to default state
        RestAssured.reset();
    }
}
