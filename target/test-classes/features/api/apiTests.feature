Feature: API Tests

  Background:
    * url 'https://fakestoreapi.com'

  Scenario: Get products
    Given path 'products'
    When method GET
    Then status 200
    And print 'Products found:', response.length

  Scenario: Create user
    Given path 'users'
    And request
    """
    {
      "email": "bonolo@test.com",
      "username": "bonolo_qa",
      "password": "Test@123",
      "name": "Bonolo Tester"
    }
    """
    When method POST
    Then status 201
    And print 'User created with ID:', response.id