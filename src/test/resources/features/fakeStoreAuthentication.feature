Feature: Fake Store Authentication Tests

  Background:
    * url 'https://fakestoreapi.com'

  Scenario: User login
    Given path '/auth/login'
    And request
    """
    {
      "username": "mor_2314",
      "password": "83r5^_"
    }
    """
    When method POST
    Then status 201
    And match response.token == '#string'
    And print 'Login Token:', response.token