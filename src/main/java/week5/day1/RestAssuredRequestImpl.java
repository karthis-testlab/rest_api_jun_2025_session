package week5.day1;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredRequestImpl implements ApiClient {
	
	private Response response;
	
	private RequestSpecification given(RequestSpecification requestSpec) {
		return RestAssured.given()
				          .spec(requestSpec); 
	}

	public ResponseAPI get(RequestSpecification requestSpec) {
		response = given(requestSpec).get();
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI get(RequestSpecification requestSpec, String endPoint) {
		response = given(requestSpec).get(endPoint);
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI post(RequestSpecification requestSpec, Object body) {		
		if (body instanceof File) {
			File file = (File) body;
			response = given(requestSpec)
				    .contentType(ContentType.JSON)
				    .when()
				    .body(file)
				    .post();			
		} else if (body instanceof String) {
			String string = (String) body;
			response = given(requestSpec)
				    .contentType(ContentType.JSON)
				    .when()
				    .body(string)
				    .post();
		} else {
			response = given(requestSpec)
				    .contentType(ContentType.JSON)
				    .when()
				    .body(body)
				    .post();
		}		
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI post(RequestSpecification requestSpec, String endPoint, Object body) {
		response = given(requestSpec)
			    .contentType(ContentType.JSON)
			    .when()
			    .body(body)
			    .post(endPoint);
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI put(RequestSpecification requestSpec, Object body) {
		if (body instanceof File) {
			File file = (File) body;
			response = given(requestSpec)
				    .contentType(ContentType.JSON)
				    .when()
				    .body(file)
				    .put();			
		} else if (body instanceof String) {
			String string = (String) body;
			response = given(requestSpec)
				    .contentType(ContentType.JSON)
				    .when()
				    .body(string)
				    .put();
		} else {
			response = given(requestSpec)
				    .contentType(ContentType.JSON)
				    .when()
				    .body(body)
				    .put();
		}
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI put(RequestSpecification requestSpec, String endPoint, Object body) {
		response = given(requestSpec)
			    .contentType(ContentType.JSON)
			    .when()
			    .body(body)
			    .put(endPoint);
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI delete(RequestSpecification requestSpec) {
		response = given(requestSpec).delete();
		return new RestAssuredResponseImpl(response);
	}

	public ResponseAPI delete(RequestSpecification requestSpec, String endPoint) {
		response = given(requestSpec).delete(endPoint);
		return new RestAssuredResponseImpl(response);
	}

}