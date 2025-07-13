package week5.day2;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;

import io.restassured.http.ContentType;

public class CreateBugTicket {	
	
	public static void main(String[] args) {
		
		Root body = new Root();
		Fields fields = new Fields();
		Project project = new Project();
		IssueType issueType = new IssueType();
		
		project.setKey("SB");
		issueType.setName("Bug");
		
		fields.setProject(project);
		fields.setIssuetype(issueType);
		fields.setSummary("Create new bug ticket");
		
		body.setFields(fields);
		
		given()
		 .baseUri("https://karthikeselene.atlassian.net")
		 .basePath("/rest/api/3/issue")
		 .auth()
		 .preemptive()
		 .basic("karthike.selene@gmail.com", "")
		 .contentType(ContentType.JSON)
		 .log().all()
		 .when()		 
		 .body(new Gson().toJson(body))
		 .post()
		 .then()
		 .assertThat()
		 .statusCode(201)
		 .log().all();
	}

}