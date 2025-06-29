package week3.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DataDrivenApproach {
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {
			{"REST", "API", "true"},
			{"REST1", "API1", "true"}
		}; 
	}
	
	@Test(dataProvider = "getData")
	public void createIncidents(String shortDescription, String description, String active) {
		
		IncidentPayLoad requestBody = new IncidentPayLoad();
		requestBody.setShort_description(shortDescription);
		requestBody.setDescription(description);
		requestBody.setActive(active);
		
		given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}")
		  .pathParam("tableName", "incident")
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")
		  .contentType(ContentType.JSON)
		  .log().all()
		.when()		
		  .body(requestBody)
		  .post()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(201)
		  .statusLine(Matchers.containsString("Created"))
		  .contentType(ContentType.JSON);
		
	}

}