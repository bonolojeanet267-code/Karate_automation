@RequestLoan
Feature: Request Loan

  Background:

    * configure driver = { type: 'chrome' }

  Scenario: Apply For Loan

    Given driver parabankUrl

    And input("input[name='username']", "john")
    And input("input[name='password']", "demo")
    And click("input[value='Log In']")

    And waitFor("a[href*='requestloan.htm']")
    And click("a[href*='requestloan.htm']")

    And input('#amount', '10000')
    And input('#downPayment', '1000')

    When click("input[value='Apply Now']")

    Then waitFor('body')