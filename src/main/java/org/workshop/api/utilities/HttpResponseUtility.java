package org.workshop.api.utilities;

import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HttpResponseUtility {

	/**
	 * Get status code from the response
	 * 
	 * @param response
	 * @return StatusCode
	 */
	public static int GetStatusCode(Response response) {
		int StatusCode = response.jsonPath().get("status_code");
		return StatusCode;
	}

	/**
	 * Get cookies from response
	 * @param response
	 * @return
	 */
	public static Map<String, String> GetCookies(Response response) {
		return response.getCookies();
	}

	/**
	 * Convert response to string
	 * 
	 * @param response
	 * @return JsonResult
	 */
	public static String GetJsonResult(Response response) {
		String JsonResult = response.asString();
		return JsonResult;
	}
	/**
	 * Convert response to json object
	 * @param response
	 * @return
	 */
    public static JsonPath getJsonPath (Response response) {
        String json = response.asString();
        return new JsonPath(json);
    }
	/**
	 * Get value as String from the Json response providing path of the value
	 * 
	 * @param response
	 * @param Value
	 * @return JsonData (Value)
	 */

	public static String GetJsonDataStringValue(Response response, String Path) {
		String JsonData = response.jsonPath().getString(Path);
		return JsonData;
	}

	/**
	 * Get value as Int from the Json response providing path of the value
	 * 
	 * @param response
	 * @param Path
	 * @return JsonData (Value)
	 */

	public static int GetJsonDataIntValue(Response response, String Path) {
		int JsonData = response.jsonPath().getInt(Path);
		return JsonData;
	}
}