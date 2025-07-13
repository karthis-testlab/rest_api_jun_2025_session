package week5.day2;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.http.ContentType;

public class JiraIssueAttachment {

	public static void main(String[] args) {
		
		given()
		 .baseUri("https://karthikeselene.atlassian.net")
		 .basePath("/rest/api/3/issue/{issueIdOrKey}")
		 .auth()
		 .preemptive()
		 .basic("karthike.selene@gmail.com", "")
		 .pathParam("issueIdOrKey", "10474")
		 .contentType(ContentType.MULTIPART)
		 .header("X-Atlassian-Token", "no-check")
		 .log().all()
		 .when()		 
		 .multiPart(new File("./020619_TSS_RESTful-URL.png"))
		 .post("/attachments")
		 .then()
		 .assertThat()
		 .statusCode(200);

	}

}