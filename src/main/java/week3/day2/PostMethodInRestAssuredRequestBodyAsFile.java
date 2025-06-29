package week3.day2;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class PostMethodInRestAssuredRequestBodyAsFile {	
			
	public static void main(String[] args) {
		
		File file = new File("src/main/resources/request_payload/create_record.json");
		
		given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}")
		  .pathParam("tableName", "incident")
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")
		  .contentType(ContentType.JSON)
		  .log().all()
		.when()
		  .body(file)
		  .post()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(201)
		  .statusLine(Matchers.containsString("Created"))
		  .contentType(ContentType.JSON);		

	}

}