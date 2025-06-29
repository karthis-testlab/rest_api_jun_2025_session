package week3.day2;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class PostMethodInRestAssuredRequestBodyAsPojoObject {	
			
	public static void main(String[] args) {
		
		IncidentPayLoad requestBody = new IncidentPayLoad();
		requestBody.setShort_description("REST");
		requestBody.setDescription("Create record using POJO Object");
		requestBody.setActive("true");
		
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