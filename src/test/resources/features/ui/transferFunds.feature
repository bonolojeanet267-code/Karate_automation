Feature: Transfer Funds

  Background:

    * configure driver = { type: 'chrome' }

  Scenario: Transfer Money Successfully

    Given driver 'https://parabank.parasoft.com/parabank/index.htm'

    And input("input[name='username']", "john")
    And input("input[name='password']", "demo")
    And click("input[value='Log In']")

    And click("a[href*='transfer.htm']")

    And input("#amount", "500")

    When click("input[value='Transfer']")

    Then waitFor("h1.title")
    And match text("h1.title") contains 'Transfer Complete'