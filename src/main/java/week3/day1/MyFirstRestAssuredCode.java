package week3.day1;

import io.restassured.RestAssured;

public class MyFirstRestAssuredCode {

	public static void main(String[] args) {	
		
		// Pre-Request
		RestAssured.given()		           
		           	.baseUri("https://dev265761.service-now.com")
		           	.basePath("/api/now/table/")
		           	.auth()		           	
		           	.basic("admin", "d@9IvhOh5DR*")
		           	// This will print request information in the console
		           	.log().all()
		           // Call HTTP Methods
		           .when()		
		           	.get("incident")	
		           // Response Assertion
		           .then()
		            // Response information in the console
		            .log().all()
		            .assertThat()
		            .statusCode(200);


	}

}