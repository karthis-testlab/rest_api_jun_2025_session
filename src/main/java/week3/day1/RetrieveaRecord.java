package week3.day1;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class RetrieveaRecord {

	public static void main(String[] args) {
		
		Map<String, Object> pathParams = new HashMap<String, Object>();
		pathParams.put("tableName", "incident");
		pathParams.put("sys_id", "ffef35afc391221082c2feac0501312b");
		
		given()
		 .baseUri("https://dev265761.service-now.com")
		 .basePath("/api/now/table/{tableName}/{sys_id}")
		 .pathParams(pathParams)
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