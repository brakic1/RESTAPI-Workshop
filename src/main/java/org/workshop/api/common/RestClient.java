package org.workshop.api.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @method Method that is tested
 * @requestSpecification specified headers and paths under test
 * @return null if method is not supported
 */
public final class RestClient {
	// Private constructor to prevent instantiation
	private RestClient() {
		throw new UnsupportedOperationException();
	}

	public static Response getResponseForMethod(RestMethods method, RequestSpecification requestSpecification) {

		switch (method) {
		case GET:
			return getRequest(requestSpecification);
		case POST:
			return postRequest(requestSpecification);
		case DELETE:
			return deleteRequest(requestSpecification);
		case PUT:
			return putRequest(requestSpecification);
		default:
			return null;
		}
	}

	/**
	 * Execute POST request
	 * 
	 * @param requestSpecification
	 * @return RestAssured Response
	 */
	private static Response postRequest(RequestSpecification requestSpecification) {
		return RestAssured.given().spec(requestSpecification).log().all().relaxedHTTPSValidation().when().post().then()
				.log().all().extract().response();
	}

	/**
	 * Execute GET request
	 * 
	 * @param requestSpecification
	 * @return RestAssured Response
	 */
	private static Response getRequest(RequestSpecification requestSpecification) {
		return RestAssured.given().spec(requestSpecification).log().all().relaxedHTTPSValidation().when().get().then()
				.log().all().extract().response();
	}

	/**
	 * Execute DELETE request
	 * 
	 * @param requestSpecification
	 * @return RestAssured Response
	 */
	private static Response deleteRequest(RequestSpecification requestSpecification) {
		return RestAssured.given().spec(requestSpecification).log().all().relaxedHTTPSValidation().when().delete()
				.then().log().all().extract().response();
	}

	/**
	 * Execute PUT request
	 * 
	 * @param requestSpecification
	 * @return RestAssured Response
	 */
	private static Response putRequest(RequestSpecification requestSpecification) {
		return RestAssured.given().spec(requestSpecification).log().all().relaxedHTTPSValidation().when().put().then()
				.log().all().extract().response();
	}
}
