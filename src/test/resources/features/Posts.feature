Feature: List of Posts API

  @JPMC
  Scenario: Verify the retrival of List of Posts
    Given the posts list api is available
    When I send a GET request to the "/posts" endpoint
    Then The response status code should be "200"
    And The response should contain all the created posts

  @JPMC
  Scenario Outline: Verify that user is able to search all the posts created by a particular user
    Given the posts list api is available
    When I send a GET request to the "/posts" endpoint with query param of "userId" having value as "<userId>"
    Then The response status code should be "200"
    And the response should contain all the posts created by the specified user

    Examples: 
      | userId |
      |      1 |
      |      2 |

  @JPMC
  Scenario Outline: Verify that the user is able to search the post by title
    Given the posts list api is available
    When I send a GET request to the "/posts" endpoint with query param of "title" having value as "<title>"
    Then The response status code should be "200"
    And the response should contain all the posts that matches with the given "<title>"

    Examples: 
      | title                |
      | eum et est occaecati |
      | magnam facilis autem |

  @JPMC
  Scenario: Verify the creation of a new post
    Given the create posts api is available
    When I send a POST request to the "/posts" endpoint to create a new post with body
    Then The response status code should be "201"
    And the response should contain the newly created post

  @JPMC
  Scenario Outline: Verify that user is able to update the existing post
    Given the update posts api is available
    When I send a PUT request to the "/posts" endpoint to update the existing post <id> with "<title>" , "<body>" by user "<userId>"
    Then The response status code should be "200"
    And the response should contain the updated post details

    Examples: 
      | id | title         | body                     | userId |
      |  1 | Updated title | This body is now updated |      1 |

  @JPMC
  Scenario: Verify that user is able to delete the existing post
    Given the delete posts api is available
    When I send a DELETE request to the "/posts" endpoint to delete an existing post with postId 1
    Then The response status code should be "200"
    And the response should not contain the deleted post
