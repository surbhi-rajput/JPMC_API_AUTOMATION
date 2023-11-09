package stepDefinitions;

import java.util.Map;
import org.testng.Assert;
import commonMethods.APIResponse;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

public class CommentsStepDefinition {

	@Then("The response should give the comments list")
	public void validateListOfComments() {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> comment = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Comments List Response is empty");
		Assert.assertEquals(true, comment.containsKey("id"), "Comment Id key is missing in response");
		Assert.assertEquals(true, comment.containsKey("postId"), "Post Id key is missing in response");
		Assert.assertEquals(true, comment.containsKey("name"), "User name key is missing in response");
		Assert.assertEquals(true, comment.containsKey("email"), "User email key is missing in response");
		Assert.assertEquals(true, comment.containsKey("body"), "Post body key is missing in response");
	}

	@Then("I should get the count of comments on the post")
	public void validateCommentsCountOnAPost() {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		System.out.println("Total Count of comments : "+jsonPath.getList("").size());
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Comments List Response is empty");
	}

	@Then("the response should contain all the comments posted by specified email")
	public void validateCommentsFromEmail() {
		System.out.println("Response -> " + APIResponse.getResponse().asString());
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> comment = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Comments List Response is empty");
		Assert.assertEquals(comment.get("email"), APIResponse.getQueryParams().get("email"),
				"The response does not meet the search criteria");
	}

	@Then("the response should contain all the comments posted by the users having specified name")
	public void validateCommentsFromUserName() {
		System.out.println("Response -> " + APIResponse.getResponse().asString());
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> comment = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Comments List Response is empty");
		Assert.assertEquals(comment.get("name"), APIResponse.getQueryParams().get("name"),
				"The response does not meet the search criteria");
	}

	@Then("the response should contain all the comments on the post having {string}")
	public void validateCommentsOnParticularPost(String postId) {
		System.out.println("Response -> " + APIResponse.getResponse().asString());
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> comment = jsonPath.getMap("[0]");
		System.out.println("Count of comments on the post having post Id as "+postId+" : "+jsonPath.getList("").size());
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "Comments List Response is empty");
		Assert.assertEquals(""+comment.get("postId"), postId,
				"The response does not meet the search criteria");
	}

}
