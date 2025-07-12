package com.matschie.api.rest.assured.base;

import java.util.HashMap;
import java.util.Map;

import com.matschie.api.design.ResponseAPI;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class RestAssuredResponseImpl implements ResponseAPI {
	
	private Response response;
	
	public RestAssuredResponseImpl(Response response) {
		this.response = response;
	}

	public int getStatusCode() {		
		return response.getStatusCode();
	}

	public String getStatusMessage() {	
		String[] strings = response.getStatusLine().split(" ", 3);
		return strings[strings.length - 1];
	}

	public String getBody() {
		return response.getBody().asPrettyString();
	}

	public Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		Headers allHeaders = response.headers(); 
		for (Header header : allHeaders) {
			headers.put(header.getName(), header.getValue());
		}
		return headers;
	}

	public String getContentType() {
		String[] values = response.getContentType().split(";");
		return values[0];
	}

	@Override
	public long getTime() {		
		return response.getTime();
	}

}