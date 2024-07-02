Feature: user should be able to create a category
  @createCategory
  Scenario: verify user successfully creates a category
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Food" in request body
    And I have "category_description" with "foods and drinks" in request body
    And I have "flag" with "true" in request body
    When I send POST request
    Then I retrieve id for "category_id"
    Then verify status code is 201
    Then I delete "Food" category in database
    Then I verify "Food" is deleted from database using GET request


  Scenario: verify flag accepts only boolean
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Food" in request body
    And I have "category_description" with "foods and drinks" in request body
    And I have "flag" with "real madrid" in request body
    When I send POST request
    Then verify status code is 500


  @createCate @smoke
  Scenario: verify user successfully creates a category
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Food" in request body
    And I have "category_description" with "foods and drinks" in request body
    And I have "flag" with "FALSE" in request body
    When I send POST request
    Then verify status code is 201


  Scenario Outline: verify flag accepts only boolean
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Food" in request body
    And I have "category_description" with "foods and drinks" in request body
    And I have "flag" with "<valueOfflag>" in request body
    When I send POST request
    Then verify status code is 500
    Examples:
      | valueOffla  |  |
      | real madrid |  |
      | chelsea     |  |
      | liverpool   |  |
      | "2132323"     |  |
      | 222.2222    |  |
      | "true"        |  |



  Scenario Outline: verify flag accepts only boolean
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "<valueOfTitle>" in request body
    And I have "category_description" with "foods and drinks" in request body
    And I have "flag" with "true" in request body
    When I send POST request
    Then verify status code is 500
    Examples:
      | valueOfTitle |  |
      | sw madrid    |  |
      | chelsea      |  |
      | liverpool    |  |
      | 23           |  |
      | 222@.2222    |  |
      | 2@!          |  |


