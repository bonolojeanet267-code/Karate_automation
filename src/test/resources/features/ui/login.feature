Feature: Swag Labs Login

Scenario: Successful login with valid credentials

    * configure driver = { type: 'chrome' }

    Given driver 'https://www.saucedemo.com/'

    And input('#user-name', 'standard_user')
    And input('#password', 'secret_sauce')

    When click('#login-button')

    Then waitFor('.inventory_list')

    And match driver.url contains 'inventory'