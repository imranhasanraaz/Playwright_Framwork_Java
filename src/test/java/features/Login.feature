Feature: Login feature

  Background:
    Given User is on the login page

  @sanity @login
  Scenario: User try to login with valid credentials
    When User input username "standard_user"
    And User input password "secret_sauce"
    And User click on the login button
    Then User successfully logged in
    And User should see the inventory page

  @login @negative
  Scenario Outline: User try to login invalid credentials
    When User input username <username>
    And User input password <password>
    And User click on the login button
    Then User should be on the log in page
    And User should see an error message <error_message>
    Examples:
      | username        | password       | error_message                                                               |
      | "standard_user" | "secretsauce"  | "Epic sadface: Username and password do not match any user in this service" |
      | "standard_user" | ""             | "Epic sadface: Password is required"                                        |
      | ""              | "secret_sauce" | "Epic sadface: Username is required"                                        |
      | ""              | ""             | "Epic sadface: Username is required"                                        |
      | "standarduse"   | "secret_sauce" | "Epic sadface: Username and password do not match any user in this service" |
      | "standarduse"   | "secretsauce"  | "Epic sadface: Username and password do not match any user in this service" |
