package org.example.api.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.example.core.config.TestConfig;
import java.util.Map;

public class UserApiSteps {
    private RequestSpecification request;
    private Response response;
    private String userId;

    @Given("the API base URL is configured")
    public void theAPIBaseURLIsConfigured() {
        baseURI = TestConfig.getInstance().getProperty("api.base.url");
        request = given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");
    }

    @When("I create a new user with the following details:")
    public void iCreateNewUser(DataTable dataTable) {
        Map<String, String> user = dataTable.asMaps().get(0);
        response = request
            .body(user)
            .when()
            .post("/api/users");
    }

    @Given("a user exists with id {string}")
    public void aUserExistsWithId(String id) {
        this.userId = id;
    }

    @When("I request the user details")
    public void iRequestUserDetails() {
        response = request
            .when()
            .get("/api/users/" + userId);
    }

    @When("I update the user with the following details:")
    public void iUpdateUser(DataTable dataTable) {
        Map<String, String> updates = dataTable.asMaps().get(0);
        response = request
            .body(updates)
            .when()
            .put("/api/users/" + userId);
    }

    @When("I delete the user")
    public void iDeleteUser() {
        response = request
            .when()
            .delete("/api/users/" + userId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("the response should contain the created user details")
    public void theResponseShouldContainUserDetails() {
        response.then()
            .body("id", not(empty()))
            .body("firstName", not(empty()))
            .body("lastName", not(empty()))
            .body("email", not(empty()));
    }

    @Then("the user details should be correct")
    public void userDetailsShouldBeCorrect() {
        response.then()
            .body("id", equalTo(userId))
            .body("firstName", not(empty()))
            .body("lastName", not(empty()))
            .body("email", not(empty()));
    }

    @Then("the user details should be updated")
    public void userDetailsShouldBeUpdated() {
        response.then().body("id", equalTo(userId));
    }

    @Then("the user should no longer exist")
    public void userShouldNoLongerExist() {
        given()
            .when()
            .get("/api/users/" + userId)
            .then()
            .statusCode(404);
    }
}
