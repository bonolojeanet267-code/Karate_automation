Feature: Fake Store API Tests

  Background:
    * url 'https://fakestoreapi.com'

    Scenario: Get all products in cart

    Given path '/carts'
    When method GET
    Then status 200
    And print 'Response:', response
    
    

