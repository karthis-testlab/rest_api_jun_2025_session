package week3.day2;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class PostMethodInRestAssuredRequestBodyAsString {
	
	static String requestBody = """
			{
			  "short_description": "REST",
			  "description": "API",
			  "active": "true"
            }
			""";
			
	public static void main(String[] args) {
		
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