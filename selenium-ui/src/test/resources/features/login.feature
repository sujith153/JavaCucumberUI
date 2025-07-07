Feature: Login Functionality with Selenium
  As a registered user
  I want to login to the application
  So that I can access my account

  Scenario: Successful Login
    Given I navigate to the login page
    When I enter username "test@example.com"
    And I enter password "password123"
    And I click the login button
    Then I should be logged in successfully
    And I should see the dashboard

  Scenario Outline: Invalid Login Attempts
    Given I navigate to the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see error message "<errorMessage>"

    Examples:
      | username        | password    | errorMessage           |
      | invalid@test.com| wrong123    | Invalid credentials    |
      |                 | password123 | Username is required   |
      | test@example.com|            | Password is required   |
