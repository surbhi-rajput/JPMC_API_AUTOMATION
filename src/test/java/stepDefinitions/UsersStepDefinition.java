package stepDefinitions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import commonMethods.APIResponse;
import commonMethods.CommonApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.APIData;

public class UsersStepDefinition extends APIData {

	@Given("the create posts api is available")
	@Given("the update posts api is available")
	@Given("the delete posts api is available")
	@Given("the posts list api is available")
	@Given("the comments list api is available")
	@Given("The user list API is available")
	public void getApiUrl() {
		RestAssured.baseURI = BASE_URL;
	}

	@When("I send a GET request to the {string} endpoint")
	public void sendGetRequest(String endPoint) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		APIResponse
				.setResponse(CommonApiUtils.sendGetRequest(endPoint, headers, Collections.<String, String>emptyMap()));
	}

	@When("I send a GET request to the {string} endpoint to filter users by city {string}")
	public void sendGetRequestWithCity(String endPoint, String SearchValue) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("address.city", SearchValue);
		APIResponse.setQueryParams(queryParamsMap);
		APIResponse.setResponse(CommonApiUtils.sendGetRequest(endPoint, headers, queryParamsMap));
	}

	@When("I send a GET request to the {string} endpoint to filter users by company {string}")
	public void sendGetRequestWithCompany(String endPoint, String SearchValue) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("company.name", SearchValue);
		APIResponse.setQueryParams(queryParamsMap);
		APIResponse.setResponse(CommonApiUtils.sendGetRequest(endPoint, headers, queryParamsMap));
	}

	@When("I send a GET request to the {string} endpoint with query param of {string} having value as {string}")
	public void sendGetRequestWithQueryParams(String endPoint, String SearchParam, String SearchValue) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ContentType", "application/json");
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put(SearchParam, SearchValue);
		APIResponse.setQueryParams(queryParamsMap);
		APIResponse.setResponse(CommonApiUtils.sendGetRequest(endPoint, headers, queryParamsMap));
	}

	@Then("The response status code should be {string}")
	public void assertStatusCode(String statusCode) {
		Assert.assertEquals(""+APIResponse.getResponse().getStatusCode(), statusCode , "The response status code is incorrect");
	}

	@Then("The response should contain a list of users")
	public void validateListOfUsers() {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> user = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "User List Response is empty");
		Assert.assertEquals(true, user.containsKey("id"), "User Id key is missing in response");
		Assert.assertEquals(true, user.containsKey("name"), "User name key is missing in response");
		Assert.assertEquals(true, user.containsKey("name"), "User name key is missing in response");
		Assert.assertEquals(true, user.containsKey("email"), "User email key is missing in response");
		Assert.assertEquals(true, user.containsKey("username"), "User username key is missing in response");
		Assert.assertEquals(true, user.containsKey("phone"), "User phone key is missing in response");
		Assert.assertEquals(true, user.containsKey("website"), "User website key is missing in response");
		Assert.assertEquals(true, user.containsKey("address"), "User address key is missing in response");
	}

	@Then("the response should contain users matching the {string} and {string}")
	public void validateSearchedUser(String SearchParam, String SearchValue) {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> user = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "User List Response is empty");
		Assert.assertEquals((String)SearchValue, ""+user.get(SearchParam), "The response does not meet the search criteria");
		Assert.assertEquals(true, user.containsKey("id"), "User Id key is missing in response");
		Assert.assertEquals(true, user.containsKey("name"), "User name key is missing in response");
		Assert.assertEquals(true, user.containsKey("name"), "User name key is missing in response");
		Assert.assertEquals(true, user.containsKey("email"), "User email key is missing in response");
		Assert.assertEquals(true, user.containsKey("username"), "User username key is missing in response");
		Assert.assertEquals(true, user.containsKey("phone"), "User phone key is missing in response");
		Assert.assertEquals(true, user.containsKey("website"), "User website key is missing in response");
		Assert.assertEquals(true, user.containsKey("address"), "User address key is missing in response");
	}

	@Then("the response should contain a list of users living in the given city")
	public void validateSearchedUserInACity() {
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		System.out.println("Response -> " + APIResponse.getResponse().asString());
		Map<String, Object> user = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "User List Response is empty");
		if (user != null && user.containsKey("address") && user.get("address") instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> address = (Map<String, Object>) user.get("address");
			Assert.assertEquals(address.get("city"), APIResponse.getQueryParams().get("address.city"),
					"The response does not meet the search criteria");

		}
		else
			Assert.assertEquals(false, true,"Response is incorrect");
	}

	@Then("the response should contain a list of users working in the given company")
	public void validateSearchedUserInACompany() {
		System.out.println("Response -> " + APIResponse.getResponse().asString());
		JsonPath jsonPath = APIResponse.getResponse().jsonPath();
		Map<String, Object> user = jsonPath.getMap("[0]");
		Assert.assertNotEquals(0, jsonPath.getList("").size(), "User List Response is empty");
		if (user != null && user.containsKey("company") && user.get("company") instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> company = (Map<String, Object>) user.get("company");
			Assert.assertEquals(company.get("name"), APIResponse.getQueryParams().get("company.name"),
					"The response does not meet the search criteria");

		}
		else
			Assert.assertEquals(false, true,"Response is incorrect");
	}
}
