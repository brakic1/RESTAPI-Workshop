package org.workshop.api.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CommonUtility {

	/**
	 * Set Base URI
	 * 
	 * @param url
	 */
	public static void setBaseURI(String url) {
		RestAssured.baseURI = url;
	}

	/**
	 * Sets base path
	 * 
	 * @param basePathTerm
	 */
	public static void setBasePath(String basePathTerm) {
		RestAssured.basePath = basePathTerm;
	}

	/**
	 * Reset Base URI (after test)
	 */
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	/**
	 * Reset base path
	 */
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	/**
	 * Set ContentType
	 * 
	 * @param Type
	 */
	public static void setContentType(ContentType Type) {
		RestAssured.given().contentType(Type);
	}

	/**
	 * Create search query path
	 * 
	 * @param param
	 * @param paramValue
	 */
	public static String createQueryParamPath(String param, String paramValue) {
		return "?" + param + "=" + paramValue;
	}
}
