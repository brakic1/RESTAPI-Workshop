package org.workshop.api.client;

import static io.restassured.RestAssured.given;
import static org.workshop.api.client.Constants.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.workshop.api.models.Employee;
import org.workshop.api.models.NewEmployee;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import com.google.gson.Gson;


public class EmployeeActions {
	
	    private RequestSpecification requestSpecification;

	    public EmployeeActions() {
	        requestSpecification = new RequestSpecBuilder()
	                .setBaseUri(BASE_URL)
	                .setContentType(ContentType.JSON)
	                .log(LogDetail.ALL).build();
	    }

	    public NewEmployee addNewEmployee(NewEmployee request) {
	         Response response = given(requestSpecification)
	                .body(request, ObjectMapperType.GSON)
	                .post(CREATE_ENDPOINT);
	         Type newEmployeeType = new TypeToken<NewEmployee>(){}.getType();
	         return convertResponseToObj(response, newEmployeeType);
	    }

	    public Employee getEmployeeById(String id) {
	         Response response = given(requestSpecification)
	                .pathParam("id", id)
	                .get(EMPLOYEE_ENDPOINT + "/{id}");
	         Type employeeType = new TypeToken<Employee>(){}.getType();
	         return convertResponseToObj(response, employeeType);
	    }

	    public void deleteEmployee(String employeeId) {
	         given(requestSpecification)
	        	.pathParam("id", employeeId)
	            .expect().statusCode(200)
	         .when()
	             .delete(DELETE_ENDPOINT + "/{id}");
	    }

	    public Response deleteEmployee(NewEmployee employee) {
	         return given(requestSpecification)
	        	.pathParam("id", employee.getId())
	        	.expect().statusCode(200)
	    	 .when()
	            .delete(DELETE_ENDPOINT + "/{id}");
	    }

	    public boolean isEmployeeExists(Employee employee) {
	        return isEmployeeExists(employee.getId());
	    }

	    public boolean isEmployeeExists(String employeeId) {
	        Response response = given(requestSpecification)
	                .pathParam("id", employeeId)
	                .get(EMPLOYEE_ENDPOINT + "/{id}");
	        return (response.getStatusCode()==200);
	    }
	    
	    public static <T> T convertResponseToObj (Response response, Type type) {
	    	String json = response.getBody().asString();
			//Creation of JsonPath object
			Gson g = new Gson();
			T t =  g.fromJson(json, type);
			return t;
	    }
}
