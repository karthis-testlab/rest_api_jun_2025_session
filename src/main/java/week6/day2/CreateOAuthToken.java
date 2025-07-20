package week6.day2;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class CreateOAuthToken {

	public static void main(String[] args) {		 
		
		 String access_token = given()
		  .baseUri("https://dev209663.service-now.com")
		  .basePath("/oauth_token.do")
		  .contentType(ContentType.URLENC)
		  .log().all()
		  .when()
		  .formParam("grant_type", "password")
		  .formParam("client_id", "86d5179e62322210f3ce6e390980b049")
		  .formParam("client_secret", "|Spd.,h;AU")
		  .formParam("username", "admin")
		  .formParam("password", "qy/Q6A=vOM3x")
		  .post()
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"))
		  .extract()
		  .jsonPath()
		  .getString("access_token");
		 
		 given()
		   .baseUri("https://dev209663.service-now.com")
		   .basePath("/api/now/table/{tableName}")
		   .pathParam("tableName", "incident")
		   .header("Authorization", "Bearer "+access_token)
		   .log().all()
		   .when()
		   .get()
		   .then()
		   .log().all()
		   .assertThat()
		   .statusCode(200);		  

	}

}
