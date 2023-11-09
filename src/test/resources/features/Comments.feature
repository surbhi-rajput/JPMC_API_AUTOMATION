Feature: Comments on Post API

  @JPMC
  Scenario: Verify the retrival of all comments on all the posts in the social network
    Given the comments list api is available
    When I send a GET request to the "/comments" endpoint
    Then The response status code should be "200"
    And The response should give the comments list

  @JPMC
  Scenario Outline: Verify that the user is able to search and count the comments present on a particular post
    Given the comments list api is available
    When I send a GET request to the "/comments" endpoint with query param of "postId" having value as "<postId>"
    Then The response status code should be "200"
    And the response should contain all the comments on the post having "<postId>"
    And I should get the count of comments on the post
    Examples: 
      | postId |
      |      1 |
      |      2 |

  @JPMC
  Scenario Outline: Verify that the user is able to search and count the comments posted by a particular emailId
    Given the comments list api is available
    When I send a GET request to the "/comments" endpoint with query param of "email" having value as "<email>"
    Then The response status code should be "200"
    And the response should contain all the comments posted by specified email
    Examples: 
      | email             |
      | Nikita@garfield.biz |
      | Hayden@althea.biz   |

  @JPMC
  Scenario: Verify the comments posted by a user having a specified name
    Given the comments list api is available
    When I send a GET request to the "/comments" endpoint with query param of "name" having value as "alias odio sit"
    Then The response status code should be "200"
    And the response should contain all the comments posted by the users having specified name