@TransferFunds
Feature: Transfer Funds

  Background:

    * configure driver = { type: 'chrome' }

  Scenario: Transfer Money Successfully

    Given driver parabankUrl

    And input("input[name='username']", "john")
    And input("input[name='password']", "demo")
    And click("input[value='Log In']")

    And waitFor("a[href*='transfer.htm']")
    And click("a[href*='transfer.htm']")

    And input("#amount", "500")

    When click("input[value='Transfer']")

    Then waitFor("h1.title")
    And match text("h1.title") contains 'Transfer Complete'