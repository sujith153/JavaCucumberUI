package org.example.selenide.stepdefs;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class GoogleSearchSteps {

    @Given("I am on Google search page")
    public void iAmOnGoogleSearchPage() {
        open("https://www.google.com");
    }

    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        $("[name='q']").setValue(searchTerm).pressEnter();
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        $("#search").shouldBe(visible);
    }

    @Then("the first result should contain {string}")
    public void theFirstResultShouldContain(String expectedText) {
        $("#search").findAll(".g").first().$("h3").shouldHave(text(expectedText));
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedTitle) {
        title().contains(expectedTitle);
    }
}
