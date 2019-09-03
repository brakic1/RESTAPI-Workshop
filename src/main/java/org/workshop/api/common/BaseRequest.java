package org.workshop.api.common;

import java.util.LinkedHashMap;
import java.util.Set;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    private String host;
    private String path;
    private ContentType contentType;
    private LinkedHashMap<String, String> headers;
    private LinkedHashMap<String, Object> queryParams;
    private LinkedHashMap<String, Object> pathParams;
    private Response response;
    private String requestBody;
    private ObjectMapperType mapper;
    
    
    public BaseRequest() {
    }
    
    public void setHost (String host) {
    	this.host = host;
    }
    
    public String getHost() {
    	return host;
    }

    public void setPath (String path) {
    	this.path = path;
    }
    
    public String getPath() {
    	return path;
    }
    
    public void headerInit() {
    	this.headers = new LinkedHashMap<String, String>();
    }
    
    public void setHeader(String key, String value) {
    	headerInit();
    	this.headers.put(key, value);
    }
    
    public void setAllHeaders(LinkedHashMap<String,String> headers) {
    	headerInit();
    	this.headers.putAll(headers);
    }
    
    public Set<?> getHeaders() {
    	return headers.entrySet();
    }
    
    public void setContentType (ContentType contentType) {
    	this.contentType = contentType;
    }
    
    public void setRequestBody(String requestBody) {
    	this.requestBody = requestBody;
    }
    
    public void setRequestBodyMapper(ObjectMapperType objectMapperType) {
    	this.mapper = objectMapperType;
    }
    
    public void queryParamsInit() {
    	this.queryParams = new LinkedHashMap<String, Object>();
    }
    
    public void setQueryParams(String key, Object value) {
    	queryParamsInit();
    	this.queryParams.put(key, value);
    }
    
    public void pathParamsInit() {
    	this.pathParams = new LinkedHashMap<String, Object>();
    }
    
    public void setPathParams (String key, Object value) {
    	pathParamsInit();
    	this.pathParams.put(key, value);
    }
    
    private String getFirstKey(LinkedHashMap<String, Object> params) {
    	return params.keySet().stream().findFirst().get();
    }
    
    private Object getFirstValue(LinkedHashMap<String, Object> params) {
    	return params.get(getFirstKey(params));
    }
    
    public void cleanUp(){
        this.response = null;
    }
    
    public Response doGetRequest(){
        return this.response = RestClient.getResponseForMethod("GET", getRequestSpecification());
    }

    public Response doPostRequest(){
        return this.response = RestClient.getResponseForMethod("POST", postRequestSpecification());
    }
    
    public Response doPutReques(){
        return this.response = RestClient.getResponseForMethod("PUT", postRequestSpecification());
    }
    
    public Response doDeleteRequest(){
        return this.response = RestClient.getResponseForMethod("DELETE", getRequestSpecification());
    }

    public Response doGetRequestWithQueryParm(){
        return this.response = RestClient.getResponseForMethod("GET", getRequestSpecificationWithQueryParm());
    }

    public Response doPostRequestWithQueryParm(){
        return this.response = RestClient.getResponseForMethod("POST", postRequestSpecificationWithQueryParm());
    }
    
    public Response doPutRequestWithQueryParm(){
        return this.response = RestClient.getResponseForMethod("PUT", postRequestSpecificationWithQueryParm());
    }
    
    public Response doDeleteRequestWithQueryParm(){
        return this.response = RestClient.getResponseForMethod("DELETE", getRequestSpecificationWithQueryParm());
    }

    public Response doGetRequestWithPathParm(){
        return this.response = RestClient.getResponseForMethod("GET", getRequestSpecificationWithPathParm());
    }

    public Response doPostRequestWithPathParm(){
        return this.response = RestClient.getResponseForMethod("POST", postRequestSpecificationWithPathParm());
    }
    
    public Response doPutRequestWithPathParm(){
        return this.response = RestClient.getResponseForMethod("PUT", postRequestSpecificationWithPathParm());
    }
    
    public Response doDeleteRequestWithPathParm(){
        return this.response = RestClient.getResponseForMethod("DELETE", getRequestSpecificationWithPathParm());
    }
    
    private RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(contentType)
                .addHeaders(headers)
                .build();
    }

    private RequestSpecification postRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(contentType)
                .addHeaders(headers)
                .setBody(requestBody, mapper)
                .build();
    }
    
    private RequestSpecification getRequestSpecificationWithQueryParm(){
        return new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(contentType)
                .addHeaders(headers)
                .addQueryParam(getFirstKey(queryParams), getFirstValue(queryParams))
                .build();
    }

    private RequestSpecification postRequestSpecificationWithQueryParm(){
        return new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(contentType)
                .addHeaders(headers)
                .addQueryParam(getFirstKey(queryParams), getFirstValue(queryParams))
                .setBody(requestBody, mapper)
                .build();
    }
    
    private RequestSpecification getRequestSpecificationWithPathParm(){
        return new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(contentType)
                .addHeaders(headers)
                .addPathParam(getFirstKey(pathParams), getFirstValue(pathParams))
                .build();
    }

    private RequestSpecification postRequestSpecificationWithPathParm(){
        return new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(contentType)
                .addHeaders(headers)
                .addPathParam(getFirstKey(pathParams), getFirstValue(pathParams))
                .setBody(requestBody, mapper)
                .build();
    }
}
