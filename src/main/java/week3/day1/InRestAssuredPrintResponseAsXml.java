package week3.day1;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class InRestAssuredPrintResponseAsXml {

	public static void main(String[] args) {	
		
		// Option1: Using header method
		given()
		 .baseUri("https://dev265761.service-now.com")
		 .basePath("/api/now/table/{tableName}/{sys_id}")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", "ffef35afc391221082c2feac0501312b")
		 .header("Accept", "application/xml")
		 .auth()
		 	.basic("admin", "d@9IvhOh5DR*")
		 .log().all()
		.when()
		 .get()
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(200)
		 .contentType(ContentType.XML);
		
		// Option2: Using accept method
		given()
		 .baseUri("https://dev265761.service-now.com")
		 .basePath("/api/now/table/{tableName}/{sys_id}")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", "ffef35afc391221082c2feac0501312b")
		 .accept(ContentType.XML)
		 .auth()
		 	.basic("admin", "d@9IvhOh5DR*")
		 .log().all()
		.when()
		 .get()
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(200)
		 .contentType(ContentType.XML);
		
	}
	
}