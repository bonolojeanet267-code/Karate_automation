Feature: Fake Store Users API Tests

  Background:
    * url 'https://fakestoreapi.com'

  Scenario: Get all users
    Given path '/users'
    When method GET
    Then status 200
    And match response == '#[]'
    And print 'Users:', response

    Scenario: Add a new user
    Given path '/users'
    And request
    """
    {
      "email": "johndoe@gmail.com",
        "username": "johndoe",
        "password": "password123",
        "name": {
          "firstname": "John",
          "lastname": "Doe"
        },
        "address": {
          "city": "New York",
          "street": "123 Main St",
          "number": 1,
          "zipcode": "10001",
          "geolocation": {
            "lat": "40.7128",
            "long": "-74.0060"
          }
        },
        "phone": "123-456-7890"
    }
    """
    When method POST
    Then status 201
    And match response.id == '#number'
    And print 'Created User:', response

    Scenario: Get a user by ID
    Given path '/users/1'
    When method GET
    Then status 200
    And match response.id == 1
    And print 'User with ID 1:', response

    Scenario: Update a user
     Given path '/users/1'
        And request
        """
        {
          "email": "johndoe_updated@gmail.com"
        }
        """
        When method PUT
        Then status 200

    Scenario: Delete a user
    Given path '/users/1'
    When method DELETE
    Then status 200
    




    

