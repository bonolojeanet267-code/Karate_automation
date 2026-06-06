Feature: Login

  Scenario:

    Given driver 'https://parabank.parasoft.com/parabank/index.htm'

    * waitFor("input[name='username']")

    And input("input[name='username']", "john")
    And input("input[name='password']", "demo")

    When click("input[value='Log In']")

    * waitFor("h1.title")

    Then match text("h1.title") contains "Accounts Overview"