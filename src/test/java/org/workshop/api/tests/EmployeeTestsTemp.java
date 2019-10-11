package org.workshop.api.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.workshop.api.common.BaseRequest;
import org.workshop.api.common.RestMethods;
import org.workshop.api.models.Employee;
import org.workshop.api.models.NewEmployee;
import org.workshop.api.utilities.ConfigUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EmployeeTestsTemp extends BaseRequest {
	@BeforeClass
	public void setUp() {
		setHost(ConfigUtility.getProperty("base.url", "application.properties"));
		setContentType(ContentType.JSON);
		setRequestBodyMapper(ObjectMapperType.GSON);
		RestAssured.registerParser("text/html", Parser.JSON);
	}

	private NewEmployee createEmployee() {
		setPath(ConfigUtility.getProperty("create.endpoint", "application.properties"));
		setRequestBody(new NewEmployee());
		
		Response response = doRequest(RestMethods.POST, requestSpecificationWithBody());
		return response.getBody().as(NewEmployee.class);
	}

	private void removeEmployee(String id) {
		setPath(ConfigUtility.getProperty("delete.endpoint", "application.properties") + "/{id}");
		setPathParams("id", id);

		doRequest(RestMethods.DELETE, requestSpecificationWithPathParm());
	}

	@Test
	public void getAllEmployees() {

		// arrange
		setPath(ConfigUtility.getProperty("employees.endpoint", "application.properties"));
		// act
		Response response = doRequest(RestMethods.GET, requestSpecification());
		// assert
		List<Employee> employees = response.body().jsonPath().getList("", Employee.class);
		for (Employee employee : employees) {
			Assert.assertNotNull(employee);
		}
	}

	@Test
	public void addNewEmployee() {
		// arrange
		setPath(ConfigUtility.getProperty("create.endpoint", "application.properties"));
		NewEmployee employee = new NewEmployee();
		setRequestBody(employee);
		// act
		Response response = doRequest(RestMethods.POST, requestSpecificationWithBody());
		// assert
		NewEmployee responseObj = response.getBody().as(NewEmployee.class);
		Assert.assertEquals(responseObj.getEmployeeName(), employee.getEmployeeName());
		assertThat(employee.getEmployeeName(), equalTo(responseObj.getEmployeeName()));
		Assert.assertEquals(response.getStatusCode(), 200);
		// clean data
		removeEmployee(responseObj.getId());
	}

	@Test
	public void getEmployeeById() {
		// arrange
		String id = createEmployee().getId();
		setPath(ConfigUtility.getProperty("employee.endpoint", "application.properties") + "/{id}");
		setPathParams("id", id);
		// act
		Response response = doRequest(RestMethods.GET, requestSpecificationWithPathParm());
		// assert
		Employee responseObj = response.getBody().as(Employee.class);
		Assert.assertEquals(responseObj.getId(), id);
		// clean data
		removeEmployee(id);
	}

	@Test
	public void updateEmployee() {
		// arrange
		
		// act
	
		// assert
	
		// clean data
	}

	@Test
	public void deleteEmployee() {
		// arrange
		
		// act
		
		// assert
		
	}	
}
