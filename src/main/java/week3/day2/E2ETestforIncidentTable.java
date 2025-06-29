package week3.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;

public class E2ETestforIncidentTable {	
	
	private String sysId;
	File create_requestBody = new File("src/main/resources/request_payload/create_record.json");
	File update_requestBody = new File("src/main/resources/request_payload/update_record.json");
	
	@Test(priority = 1)
	public void createNewRecord() {
		sysId = given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}")
		  .pathParam("tableName", "incident")
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")
		  .contentType(ContentType.JSON)
		  .log().all()
		.when()
		  .body(create_requestBody)
		  .post()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(201)
		  .statusLine(Matchers.containsString("Created"))
		  .contentType(ContentType.JSON)
		  .extract()
		  .jsonPath()		  
		  .getString("result.sys_id");		
	}
	
	@Test(priority = 2)
	public void retreiveARecord() {
		given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}/{sys_id}")
		  .pathParam("tableName", "incident")
		  .pathParam("sys_id", sysId)
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")
		.when()
		  .get()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result.sys_id", Matchers.equalTo(sysId))
		  .body("result.description", Matchers.equalTo("Create record from file"));
	}
	
	@Test(priority = 3)
	public void updateExistingRecord() {
		given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}/{sys_id}")
		  .pathParam("tableName", "incident")
		  .pathParam("sys_id", sysId)
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")		  
		  .contentType(ContentType.JSON)
		  .log().all()
		.when()
		  .body(update_requestBody)
		  .put()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result.sys_id", Matchers.equalTo(sysId))
		  .body("result.description", Matchers.equalTo("Update existing record from file"))
		  .body("result.short_description", Matchers.equalTo("REST Update using Put Method"));		
	}
	
	@Test(priority = 4)
	public void deleteARecord() {
		given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}/{sys_id}")
		  .pathParam("tableName", "incident")
		  .pathParam("sys_id", sysId)
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")
		.when()
		  .delete()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(204)
		  .statusLine(Matchers.containsString("No Content"));		  
	}
	
	@Test(priority = 5)
	public void isRecordDeletedSuccessfully() {
		given()
		  .baseUri("https://dev265761.service-now.com")
		  .basePath("/api/now/table/{tableName}/{sys_id}")
		  .pathParam("tableName", "incident")
		  .pathParam("sys_id", sysId)
		  .auth()
		  	.basic("admin", "d@9IvhOh5DR*")
		.when()
		  .get()
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(404)
		  .statusLine(Matchers.containsString("Not Found"))
		  .contentType(ContentType.JSON);
	}

}