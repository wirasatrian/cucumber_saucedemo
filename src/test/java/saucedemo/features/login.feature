Feature: Login to SauceDemo

  @SDTag @Positive
  Scenario: Successful login with valid credential
    Given I am on the SauceDemo login page
    When I enter the username "visual_user"
    And I enter a password "secret_sauce"
    And I click the login button
    Then I should see the products page

  @SDTag @Negative
  Scenario: Failed login with wrong credential
    Given I am on the SauceDemo login page
    When I enter the username "wrong_user"
    And I enter a password "wrong_password"
    And I click the login button
    Then I should see an error message

  @TDD
  Scenario Outline: login
    Given On the SauceDemo login page
    When I enter <username> as username
    And I enter <password> as password
    And I clicked the login button
    Then I verify login <status> as status
    Examples:
      | username        | password        | status       |
      | standard_user   | secret_sauce    | success      |
      | wrong_user      | wrong_password  | failed       |