package stepDefinitions;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import apiGettersAndSetters.addPost;
import commonMethods.APIResponse;
import commonMethods.CommonApiUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

public class PostsStepDefinition {
	
	@Then("The response should contain all the created posts")
	public void validateListOfPosts() {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> post = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Posts List Response is empty");
		Assert.assertEquals(true, post.containsKey("id"), "Post Id key is missing in response");
		Assert.assertEquals(true, post.containsKey("userId"), "User Id key is missing in response");
		Assert.assertEquals(true, post.containsKey("title"), "post title key is missing in response");
		Assert.assertEquals(true, post.containsKey("body"), "Post body key is missing in response");
	}
	
	@Then("the response should contain all the posts created by the specified user")
	public void validateListOfPostsCreatedBySpecifiedUser() {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> post = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Posts List Response is empty");
		Assert.assertEquals(""+post.get("userId"), APIResponse.getQueryParams().get("userId"),
				"The response does not meet the search criteria");
	}
	
	@Then("the response should contain all the posts that matches with the given {string}")
	public void validateListOfPostWithSpecificTitle(String title) {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> post = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Posts List Response is empty");
		Assert.assertEquals(post.get("title"), APIResponse.getQueryParams().get("title"),
				"The response does not meet the search criteria");
	}
	
	@When("I send a POST request to the {string} endpoint to create a new post with body")
	public void sendPostRequest(String endPoint) {
		addPost ap = new addPost();
		ap.setBody("This is a sample body");
		ap.setTitle("Sample Title");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		APIResponse
				.setResponse(CommonApiUtils.sendPostRequest(endPoint, headers,ap));
	}
	
	@When("I send a PUT request to the {string} endpoint to update the existing post {int} with {string} , {string} by user {string}")
	public void sendPutRequest(String endPoint, int id, String title, String body, String userId) {
		addPost ap = new addPost();
		ap.setBody(body);
		ap.setTitle(title);
		ap.setUserId(userId);
		ap.setId(id);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		APIResponse
				.setResponse(CommonApiUtils.sendPutRequest(endPoint+"/"+ap.getId(), headers,ap));
	}
	
	@Then("the response should contain the newly created post")
	public void validateNewPost() {
		System.out.println("Response - "+APIResponse.getResponse().asString());
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Assert.assertEquals(true, jsonPath.get("id")!=null, "Posts Id is not returned");
	}
	
	@Then("the response should contain the updated post details")
	public void validateUpdatedPost() {
		System.out.println("Updated Response - "+APIResponse.getResponse().asString());
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Assert.assertEquals(true, jsonPath.get("id")!=null, "Updated Posts Id is not returned");
	}
	
	@When("I send a DELETE request to the {string} endpoint to delete an existing post with postId {int}")
	public void deleteRequest(String endPoint, int postId) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		APIResponse
				.setResponse(CommonApiUtils.sendDeleteRequest(endPoint+"/"+postId, headers));
	}
	
	@Then("the response should not contain the deleted post")
	public void validateDeletedPost() {
		System.out.println("Response - "+APIResponse.getResponse().asString());
		Assert.assertEquals("{}", APIResponse.getResponse().asString(), "Deleted response should be empty");
	}
	

}
