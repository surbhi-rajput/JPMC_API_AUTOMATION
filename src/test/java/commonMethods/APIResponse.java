package commonMethods;

import java.util.Map;

import io.restassured.response.Response;

public class APIResponse {
	private static ThreadLocal<Response> responseThreadLocal = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<Map<String, String>> queryParamsThreadLocal = ThreadLocal.withInitial(() -> null);

	public static Response getResponse() {
		return responseThreadLocal.get();
	}

	public static void setResponse(Response response) {
		responseThreadLocal.set(response);
	}
	
	public static Map<String, String> getQueryParams() {
		return queryParamsThreadLocal.get();
	}

	public static void setQueryParams(Map<String, String> queryParams) {
		queryParamsThreadLocal.set(queryParams);
	}
}
