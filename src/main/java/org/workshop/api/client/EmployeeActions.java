package org.worksop.api.client;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.List;

import org.workshop.api.models.Employee;
import org.workshop.api.models.MessageResponse;

import static org.worksop.api.client.Constants.BASE_URL;


public class EmployeeActions {
	
	    private RequestSpecification requestSpecification;

	    public EmployeeActions() {
	        requestSpecification = new RequestSpecBuilder()
	                .setBaseUri(BASE_URL)
	                .setContentType(ContentType.JSON)
	                .log(LogDetail.ALL).build();
	    }

	    public Employee addNewPet(Employee request) {
	        return given(requestSpecification)
	                .body(request)
	                .post(BASE_URL).as(Employee.class);
	    }

	    public List<Employee> EmployeeById(String id) {
	        return given(requestSpecification)
	                .pathParam("id", id)
	                .get(BASE_URL + "/employee/{id}")
	                .then().log().all()
	                .extract().body()
	                .jsonPath().getList("", Employee.class);
	    }

	    public void deleteEmployee(String employeeId) {
	        given(requestSpecification)
	                .pathParam("id", employeeId)
	                .delete(BASE_URL + "/delete/{id}");
	    }

	    public void deleteEmployee(Employee employee) {
	        given(requestSpecification)
	                .pathParam("id", employee.getId())
	                .delete(BASE_URL + "/delete/{id}");
	    }

	    public boolean isEmployeeExists(Employee employee) {
	        return isEmployeeExists(employee.getId());
	    }

	    public boolean isEmployeeExists(String employeeId) {
	        return !given(requestSpecification)
	                .pathParam("id", employeeId)
	                .get(BASE_URL + "/employee/{id}")
	                .then()
	                .extract().body().jsonPath().getObject("", MessageResponse.class)
	                .getMessage().equals("Employee not found");
	    }
}
