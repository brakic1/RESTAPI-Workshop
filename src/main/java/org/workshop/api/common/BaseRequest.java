package org.workshop.api.common;

import java.util.LinkedHashMap;
import java.util.Set;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRequest extends RestClient {

	private String host;
	private String path;
	private ContentType contentType;
	private LinkedHashMap<String, String> headers;
	private LinkedHashMap<String, Object> queryParams;
	private LinkedHashMap<String, Object> pathParams;
	@SuppressWarnings("unused")
	private Response response;
	private Object requestBody;
	private ObjectMapperType mapper;

	public BaseRequest() {
	}

	public void setUp() {
		headerInit();
		queryParamsInit();
		pathParamsInit();
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	private void headerInit() {
		this.headers = new LinkedHashMap<String, String>();
	}

	public void setHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public void setAllHeaders(LinkedHashMap<String, String> headers) {
		this.headers.putAll(headers);
	}

	public Set<?> getHeaders() {
		return headers.entrySet();
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public void setRequestBody(Object obj) {
		this.requestBody = obj;
	}

	public void setRequestBodyMapper(ObjectMapperType objectMapperType) {
		this.mapper = objectMapperType;
	}

	private void queryParamsInit() {
		this.queryParams = new LinkedHashMap<String, Object>();
	}

	public void setQueryParams(String key, Object value) {
		this.queryParams.put(key, value);
	}

	private void pathParamsInit() {
		this.pathParams = new LinkedHashMap<String, Object>();
	}

	public void setPathParams(String key, Object value) {
		this.pathParams.put(key, value);
	}

	private String getFirstKey(LinkedHashMap<String, Object> params) {
		return params.keySet().stream().findFirst().get();
	}

	private Object getFirstValue(LinkedHashMap<String, Object> params) {
		return params.get(getFirstKey(params));
	}

	public Response doRequest(String method, RequestSpecification requestSpecification) {
		return this.response = getResponseForMethod(method, requestSpecification);
	}
	
	public RequestSpecification requestSpecification() {
		return new RequestSpecBuilder().setBaseUri(host).setBasePath(path).setContentType(contentType)
				.addHeaders(headers).build();
	}

	public RequestSpecification requestSpecificationWithBody() {
		return new RequestSpecBuilder().setBaseUri(host).setBasePath(path).setContentType(contentType)
				.addHeaders(headers).setBody(requestBody, mapper).build();
	}

	public RequestSpecification requestSpecificationWithQueryParm() {
		return new RequestSpecBuilder().setBaseUri(host).setBasePath(path).setContentType(contentType)
				.addHeaders(headers).addQueryParam(getFirstKey(queryParams), getFirstValue(queryParams)).build();
	}

	public RequestSpecification requestSpecificationWithBodyAndQueryParm() {
		return new RequestSpecBuilder().setBaseUri(host).setBasePath(path).setContentType(contentType)
				.addHeaders(headers).addQueryParam(getFirstKey(queryParams), getFirstValue(queryParams))
				.setBody(requestBody, mapper).build();
	}

	public RequestSpecification requestSpecificationWithPathParm() {
		return new RequestSpecBuilder().setBaseUri(host).setBasePath(path).setContentType(contentType)
				.addHeaders(headers).addPathParam(getFirstKey(pathParams), getFirstValue(pathParams)).build();
	}

	public RequestSpecification requestSpecificationWithBodyAndPathParm() {
		return new RequestSpecBuilder().setBaseUri(host).setBasePath(path).setContentType(contentType)
				.addHeaders(headers).addPathParam(getFirstKey(pathParams), getFirstValue(pathParams))
				.setBody(requestBody, mapper).build();
	}

	public void cleanUp() {
		this.response = null;
	}
}
