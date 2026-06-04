Feature: Fake Store API Tests

  Background:
    * url 'https://fakestoreapi.com'

  @51
  Scenario: Get all products
    Given path '/products'
    When method GET
    Then status 200
    And print 'Response:', response


    @53
  Scenario: Create a new product
  Given path '/products'
  And request
    """
    {
      "title": "Test Product",
      "price": 29.99,
      "description": "A test product",
      "category": "electronics",
      "image": "https://fakestoreapi.com/img/test.png"
    }
    """
  When method POST
  Then status 201
  And match response.id == '#number'
  And print 'Created Product:', response

      @54
  Scenario: Get a product by ID
    Given path '/products/5'
    When method GET
    Then status 200
    And match response.id == 5
    And print 'Product with ID 5:', response


        @55
    Scenario: Update a product
     Given path '/products/5'
        And request
        """
        {
            "title": "Updated Product",
            "price": 39.99,
            "description": "An updated test product",
            "category": "electronics",
            "image": "https://fakestoreapi.com/img/updated.png"
        }
        """
        When method PUT
        Then status 200

          @56
    Scenario: Delete a product
    Given path '/products/4'
    When method DELETE
    Then status 200

    Scenario Outline: Get product by ID
      Given path '/products/<productId>'
      When method GET
      Then status 200
      And match response.id == <productId>
      And match response.title == '#string'
      And match response.price == '#number'
      And print 'Product:', response.title

  Examples:
    | productId |
    | 1         |
    | 2         |
    | 3         |
    | 4         |
    | 5         |
      





