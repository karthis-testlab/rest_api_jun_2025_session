package com.matschie.service.now.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.base.RestAssuredRequestImpl;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class IncidentService extends RestAssuredRequestImpl {	

	public ResponseAPI createRecord(RequestSpecification requestSpecification, Object requestPayload) {
		return post(requestSpecification, requestPayload);
	}

	public ResponseAPI retrieveRecord(RequestSpecification requestSpecification, String sysId) {		
		return get(requestSpecification, "/"+sysId);
	}

	public ResponseAPI updateExistingRecord(RequestSpecification requestSpecification, String sysId, Object requestPayload) {
		return put(requestSpecification, "/"+sysId, requestPayload);
	}

	public ResponseAPI deleteExistingRecord(RequestSpecification requestSpecification, String sysId) {
		return delete(requestSpecification, "/"+sysId);
	}

	public void validateResponse(ResponseAPI response, int statusCode, String statusLine, String contentType) {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(statusCode));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo(statusLine));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo(contentType));		
	}
	
	public void validateResponse(ResponseAPI response, int statusCode, String statusLine) {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(statusCode));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo(statusLine));
	}
	
	public void validateResponse(ResponseAPI response, int statusCode, String statusLine, String contentType, long slaInMS) {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(statusCode));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalTo(statusLine));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo(contentType));	
		MatcherAssert.assertThat(response.getTime(), Matchers.lessThan(slaInMS));
	}
	
	public String extractSysId(ResponseAPI response, String jsonPath) {
		JsonPath json = new JsonPath(response.getBody());
		return json.getString(jsonPath);
	}
	
	public void validateResponseBody(ResponseAPI response, String jsonPath, String expectedValue) {
		JsonPath json = new JsonPath(response.getBody());
		MatcherAssert.assertThat(json.getString(jsonPath), Matchers.equalTo(expectedValue));
	}

}