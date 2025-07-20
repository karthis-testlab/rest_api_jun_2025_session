package week6.day2;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import io.restassured.http.ContentType;

public class GitHubGraphQLTest {
	
	static String query = """
			query {
  viewer {
    login
    name
    email
    avatarUrl
    company
    repositories {            
      totalCount
      totalDiskUsage
    }
    followers {
      totalCount      
    }
  }
}			""";

	public static void main(String[] args) {
		
		given()
		  .baseUri("https://api.github.com")
		  .basePath("/graphql")
		  .header("Authorization", "Bearer ")
		  .contentType(ContentType.JSON)
		  .log().all()
		  .when()
		  .body(convertQueryToJsonString(query))
		  .post()
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .body("data.viewer.name", Matchers.equalTo("Karthikeyan Rajendran"));

	}
	
	private static String convertQueryToJsonString(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("query", query);
		return jsonObject.toString();
	}
	
}