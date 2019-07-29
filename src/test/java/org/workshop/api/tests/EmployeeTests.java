package org.workshop.api.tests;

import static io.restassured.RestAssured.given;
import static org.worksop.api.client.Constants.BASE_URL;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class EmployeeTests  {
	
public static String EMPLOYEE_ENDPOINT = BASE_URL + "/employee";

    @Test
    public void getEmployeeNameById() {
        given()
        	.baseUri(BASE_URL)
        	.log().everything()
        	.contentType(ContentType.JSON)
        	.pathParam("id", "1472")
        .when()
        	.get(EMPLOYEE_ENDPOINT + "/{id}")
        .then()
        	.log().body()
        	.statusCode(200)
        	.extract()
        	.path("employee_name")
        	.equals("branka");
    }
}
