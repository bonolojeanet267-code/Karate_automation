Feature: Open New Account

  Background:

    * configure driver = { type: 'chrome' }

  Scenario: Open Savings Account

    Given driver 'https://parabank.parasoft.com/parabank/index.htm'

    And input("input[name='username']", "john")
    And input("input[name='password']", "demo")
    And click("input[value='Log In']")

    And click("a[href*='openaccount.htm']")

    When click("input[value='Open New Account']")

    Then waitFor('#openAccountResult')
    And match text('#openAccountResult') contains 'Congratulations'