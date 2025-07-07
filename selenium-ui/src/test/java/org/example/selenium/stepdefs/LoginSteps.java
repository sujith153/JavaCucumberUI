package org.example.selenium.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.example.core.ui.BasePage;

public class LoginSteps extends BasePage {
    private final WebDriver driver;

    public LoginSteps() {
        driver = new ChromeDriver();
        super(driver);
    }

    @Given("I navigate to the login page")
    public void iNavigateToLoginPage() {
        driver.get("https://example.com/login");
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("I click the login button")
    public void iClickLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue(driver.findElement(By.id("dashboard")).isDisplayed());
    }

    @Then("I should see the dashboard")
    public void iShouldSeeDashboard() {
        Assert.assertEquals(driver.getTitle(), "Dashboard");
    }

    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String errorMessage) {
        String actualError = driver.findElement(By.className("error-message")).getText();
        Assert.assertEquals(actualError, errorMessage);
    }
}
