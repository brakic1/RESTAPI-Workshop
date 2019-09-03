package org.workshop.api.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.workshop.api.common.BaseRequest;
import org.workshop.api.models.Employee;
import org.workshop.api.models.NewEmployee;
import org.workshop.api.utilities.ConfigUtility;
import org.workshop.api.utilities.JsonUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class EmployeeTests extends BaseRequest {
	
private static String host;
private static String path;

	@BeforeClass
	public void setUp() {
		host = ConfigUtility.getProperty("BASE_URL", "application.properties");
		setHost(host);
		setContentType(ContentType.JSON);
		setRequestBodyMapper(ObjectMapperType.GSON);
		headerInit();
	}
	
	//@Test
	public void geAllEmployees() {

		path = ConfigUtility.getProperty("EMPLOYEES_ENDPOINT", "application.properties");
		setPath(path);
		Response response = doGetRequest();
		
		List<Employee> employees = response
				.body()
				.jsonPath().getList("", Employee.class);
	
		for (Employee employee : employees) {
			Assert.assertNotNull(employee);
		}
	}
	
	@Test
	public void addNewEmployee() {

		path = ConfigUtility.getProperty("CREATE_ENDPOINT", "application.properties");
		setPath(path);
		
		NewEmployee employee = new NewEmployee();
		
		setRequestBody(JsonUtility.POJOToJson(employee));
		RestAssured.registerParser("text/html", Parser.JSON);
		Response response = doPostRequest();
		
		NewEmployee responseObj = response.getBody().as(NewEmployee.class);
        
        Assert.assertEquals(responseObj.getEmployeeName(), employee.getEmployeeName());
	}
	
	@AfterMethod
	public void tearDown() {
		cleanUp();
	}
}
