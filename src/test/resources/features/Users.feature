Feature: List of Users API

  @JPMC
  Scenario: Verify the retrival of list of users
    Given The user list API is available
    When I send a GET request to the "/users" endpoint
    Then The response status code should be "200"
    And The response should contain a list of users

  @JPMC
  Scenario Outline: Verify that we are able to search user
    Given The user list API is available
    When I send a GET request to the "/users" endpoint with query param of "<SearchParam>" having value as "<SearchValue>"
    Then The response status code should be "200"
    And the response should contain users matching the "<SearchParam>" and "<SearchValue>"

    Examples: 
      | SearchParam | SearchValue           |
      | id          |                    10 |
      | email       | Shanna@melissa.tv     |
      | name        | Patricia Lebsack      |
      | username    | Bret                  |
      | phone       | 1-770-736-8031 x56442 |
      | website     | hildegard.org         |

  @JPMC
  Scenario: Verify that we are able to search users living in a given city
    Given The user list API is available
    When I send a GET request to the "/users" endpoint to filter users by city "Gwenborough"
    Then The response status code should be "200"
    And the response should contain a list of users living in the given city

  @JPMC
  Scenario: Verify that we are able to search users working in the given company
    Given The user list API is available
    When I send a GET request to the "/users" endpoint to filter users by company "Romaguera-Crona"
    Then The response status code should be "200"
    And the response should contain a list of users working in the given company
