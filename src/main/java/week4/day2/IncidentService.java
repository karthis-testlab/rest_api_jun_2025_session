package week4.day2;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IncidentService {	

	public Response createRecord(RequestSpecification requestSpecification, Object requestPayload) {
		if(requestPayload instanceof File) {
			File file = (File) requestPayload;
			return given()
					.spec(requestSpecification)
					.contentType(ContentType.JSON)
					.log().all()
					.when()
					.body(file)
					.post();
		} else {
			return given()
					.spec(requestSpecification)
					.contentType(ContentType.JSON)
					.log().all()
					.when()
					.body(requestPayload)
					.post();
		}
	}

	public Response retrieveRecord(RequestSpecification requestSpecification, String sysId) {
		return given()
		.spec(requestSpecification)		
		.log().all()
		.when()		
		.get("/{sys_id}", sysId);
	}

	public Response updateExistingRecord(RequestSpecification requestSpecification, String sysId, Object requestPayload) {
		if(requestPayload instanceof File) {
			File file = (File) requestPayload;
			return given()
					.spec(requestSpecification)
					.contentType(ContentType.JSON)
					.log().all()
					.when()
					.body(file)
					.put("/{sys_id}", sysId);
		} else {
			return given()
					.spec(requestSpecification)
					.contentType(ContentType.JSON)
					.log().all()
					.when()
					.body(requestPayload)
					.put("/{sys_id}", sysId);
		}
	}

	public Response deleteExistingRecord(RequestSpecification requestSpecification, String sysId) {
		return given()
		.spec(requestSpecification)		
		.log().all()
		.when()		
		.delete("/{sys_id}", sysId);
	}

	public void validateResponse(Response response, int statusCode, String statusLine, ContentType contentType) {
		response.then()
		        .assertThat()
		        .statusCode(statusCode)
		        .statusLine(Matchers.containsString(statusLine))
		        .contentType(contentType);
	}
	
	public void validateResponse(Response response, int statusCode, String statusLine) {
		response.then()
		        .assertThat()
		        .statusCode(statusCode)
		        .statusLine(Matchers.containsString(statusLine));
	}
	
	public String extractSysId(Response response, String jsonPath) {
		return response.then()
				       .extract()
				       .jsonPath()
				       .getString(jsonPath);				
	}
	
	public void validateResponseBody(Response response, String jsonPath, String expectedValue) {
		response.then()
		        .assertThat()
		        .body(jsonPath, Matchers.equalTo(expectedValue));
	}

}