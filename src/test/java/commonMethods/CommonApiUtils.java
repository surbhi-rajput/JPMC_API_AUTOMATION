package commonMethods;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonApiUtils {

	// This method is a wrapper on Rest Assured Get Request
	public static Response sendGetRequest(String endPoint, Map<String, String> headers,
			Map<String, String> queryParam) {
		RequestSpecification request = RestAssured.given();
		request.headers(headers);
		request.queryParams(queryParam);
		Response response = request.get(endPoint);
		return response;
	}

	// This method is a wrapper on Rest Assured Post Request
	public static Response sendPostRequest(String endPoint, Map<String, String> headers, Object body) {
		RequestSpecification request = RestAssured.given();
		request.headers(headers);
		request.body(body);
		Response response = request.post(endPoint);
		return response;
	}

	// This method is a wrapper on Rest Assured put Request
	public static Response sendPutRequest(String endPoint, Map<String, String> headers, Object body) {
		RequestSpecification request = RestAssured.given();
		request.headers(headers);
		request.body(body);
		Response response = request.put(endPoint);
		return response;
	}

	// This method is a wrapper on Rest Assured Delete Request
	public static Response sendDeleteRequest(String endPoint, Map<String, String> headers) {
		RequestSpecification request = RestAssured.given();
		request.headers(headers);
		Response response = request.delete(endPoint);
		return response;
	}
}
