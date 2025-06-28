package week3.day1;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class SettingQueryParameters {

	public static void main(String[] args) {
		
		given()
		 .baseUri("https://dev265761.service-now.com")
		 .basePath("/api/now/table/incident")		
		 .queryParam("sysparm_limit", "5")
		 .queryParam("sysparm_fields", "sys_id,category,short_description,description,active,number")		 
		 .auth()
		 	.basic("admin", "d@9IvhOh5DR*")		 
		 .log().all()
		.when()
		 .get()
		.then()
		 .log().all()
		 .assertThat()
		 	.statusCode(200)
		 	.statusLine(Matchers.containsString("OK"))
		 	.contentType(ContentType.JSON)
		 	.time(Matchers.lessThanOrEqualTo(5000L));

	}

}