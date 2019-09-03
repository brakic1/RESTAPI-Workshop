package org.workshop.api.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class CommonUtility {
    public static String path;
    public static String jsonPathTerm;

    /**
     * Set Base URI
     * @param url
     */
    public static void setBaseURI (String url){
        RestAssured.baseURI = url;
    }

    /**
     * Sets base path
     * @param basePathTerm
     */
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    /**
     * Reset Base URI (after test)
     */
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    /**
     * Reset base path
     */
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    /**
     * Set ContentType
     * @param Type
     */
    public static void setContentType (ContentType Type){
    	RestAssured.given().contentType(Type);
    }

    /**
     * Set Json path term
     * @param jsonPath
     */
    public static void  setJsonPathTerm(String jsonPath){
        jsonPathTerm = jsonPath;
    }

    /**
     * Created search query path
     * @param searchTerm
     * @param param
     * @param paramValue
     */
    public static void  createSearchQueryPath(String searchTerm, String param, String paramValue) {
        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
    }
}
