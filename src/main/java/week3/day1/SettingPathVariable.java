package week3.day1;

import static io.restassured.RestAssured.*;

public class SettingPathVariable {

	public static void main(String[] args) {
		
		given()
		 .baseUri("https://dev265761.service-now.com")
		 .basePath("/api/now/table/{tableName}")
		 .pathParam("tableName", "incident")
		 .auth()
		 	.basic("admin", "d@9IvhOh5DR*")
		 .log().all()
		.when()
		 .get()
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(200);

	}

}