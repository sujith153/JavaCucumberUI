Feature: User API Operations
  As an API user
  I want to perform CRUD operations on users
  So that I can manage user data

  Background:
    Given the API base URL is configured

  Scenario: Create a new user
    When I create a new user with the following details:
      | firstName | lastName | email               |
      | John      | Doe      | john.doe@email.com  |
    Then the response status code should be 201
    And the response should contain the created user details

  Scenario: Retrieve user details
    Given a user exists with id "12345"
    When I request the user details
    Then the response status code should be 200
    And the user details should be correct

  Scenario: Update user information
    Given a user exists with id "12345"
    When I update the user with the following details:
      | firstName | lastName | email                |
      | Jane      | Doe      | jane.doe@email.com   |
    Then the response status code should be 200
    And the user details should be updated

  Scenario: Delete a user
    Given a user exists with id "12345"
    When I delete the user
    Then the response status code should be 204
    And the user should no longer exist
