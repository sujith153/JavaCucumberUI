Feature: Google Search with Selenide
  As a user
  I want to search on Google
  So that I can find relevant information

  Scenario: Basic Google Search
    Given I am on Google search page
    When I search for "Selenide automation"
    Then I should see search results
    And the first result should contain "Selenide"

  Scenario Outline: Search Different Terms
    Given I am on Google search page
    When I search for "<searchTerm>"
    Then I should see search results
    And the page title should contain "<searchTerm>"
    Examples:
      | searchTerm     |
      | Java Selenium  |
      | BDD Cucumber  |
