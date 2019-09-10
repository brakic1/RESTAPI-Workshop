package org.workshop.api.tests;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.workshop.api.common.BaseRequest;
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

public class EmployeeTests extends BaseRequest {

	@BeforeClass
	public void setUp() {
		super.setUp();
		setHost(ConfigUtility.getProperty("BASE_URL", "application.properties"));
		setContentType(ContentType.JSON);
		setRequestBodyMapper(ObjectMapperType.GSON);
		RestAssured.registerParser("text/html", Parser.JSON);
	}

	private NewEmployee createEmployee() {
		setPath(ConfigUtility.getProperty("CREATE_ENDPOINT", "application.properties"));
		setRequestBody(new NewEmployee());
		
		Response response = doPostRequest();
		return response.getBody().as(NewEmployee.class);
	}

	private void removeEmployee(String id) {
		setPath(ConfigUtility.getProperty("DELETE_ENDPOINT", "application.properties") + "/{id}");
		setPathParams("id", id);

		doDeleteRequestWithPathParm();
	}

	@Test
	public void getAllEmployees() {

		// arrange
		setPath(ConfigUtility.getProperty("EMPLOYEES_ENDPOINT", "application.properties"));
		// act
		Response response = doGetRequest();
		// assert
		List<Employee> employees = response.body().jsonPath().getList("", Employee.class);
		for (Employee employee : employees) {
			Assert.assertNotNull(employee);
		}
	}

	@Test
	public void addNewEmployee() {
		// arrange
		setPath(ConfigUtility.getProperty("CREATE_ENDPOINT", "application.properties"));
		NewEmployee employee = new NewEmployee();
		setRequestBody(employee);
		// act
		Response response = doPostRequest();
		// assert
		NewEmployee responseObj = response.getBody().as(NewEmployee.class);
		Assert.assertEquals(responseObj.getEmployeeName(), employee.getEmployeeName());
		assertThat(employee.getEmployeeName(), equalTo(responseObj.getEmployeeName()));
		// clean data
		removeEmployee(responseObj.getId());
	}

	@Test
	public void getEmployeeById() {
		// arrange
		String id = createEmployee().getId();
		setPath(ConfigUtility.getProperty("EMPLOYEE_ENDPOINT", "application.properties") + "/{id}");
		setPathParams("id", id);
		// act
		Response response = doGetRequestWithPathParm();
		// assert
		Employee responseObj = response.getBody().as(Employee.class);
		Assert.assertEquals(responseObj.getId(), id);
		// clean data
		removeEmployee(id);
	}

	@Test
	public void updateEmployee() {
		// arrange
		NewEmployee employee = createEmployee();
		String id = employee.getId();
		setPath(ConfigUtility.getProperty("UPDATE_ENDPOINT", "application.properties") + "/{id}");
		setPathParams("id", id);
		// change employee and used the changed object for body request
		NewEmployee updatedEmployee = employee.name(RandomStringUtils.randomAlphabetic(10));
		setRequestBody(updatedEmployee);
		// act
		Response response = doPutRequestWithPathParm();
		// assert
		NewEmployee responseObj = response.getBody().as(NewEmployee.class);
		Assert.assertEquals(responseObj.getEmployeeName(), updatedEmployee.getEmployeeName());
		Assert.assertEquals(response.getBody().jsonPath().get("name"), updatedEmployee.getEmployeeName());
		// clean data
		removeEmployee(id);
	}

	@Test
	public void deleteEmployee() {
		// arrange
		setPathParams("id", createEmployee().getId());
		setPath(ConfigUtility.getProperty("DELETE_ENDPOINT", "application.properties") + "/{id}");
		// act
		Response response = doDeleteRequestWithPathParm();
		// assert
		Assert.assertTrue(
				response.getBody().asString().endsWith("{\"success\":{\"text\":\"successfully! deleted Records\"}}"));
	}

	@Test
	public void deleteEmployeeUsingMatchers() {
		// arrange
		String path = ConfigUtility.getProperty("DELETE_ENDPOINT", "application.properties");
		String baseUrl = ConfigUtility.getProperty("BASE_URL", "application.properties");
		// act and assert
		RestAssured.delete(baseUrl + path + "/" + createEmployee().getId()).then().assertThat()
				.body(equalTo("{\"success\":{\"text\":\"successfully! deleted Records\"}}"));
	}

	@AfterMethod
	public void tearDown() {
		cleanUp();
	}
}
