package com.matschie.api.design;

import io.restassured.specification.RequestSpecification;

public interface ApiClient {
	
	ResponseAPI get(RequestSpecification requestSpec);
	ResponseAPI get(RequestSpecification requestSpec, String endPoint);
	ResponseAPI post(RequestSpecification requestSpec, Object body);
	ResponseAPI post(RequestSpecification requestSpec, String endPoint, Object body);
	ResponseAPI put(RequestSpecification requestSpec, Object body);
	ResponseAPI put(RequestSpecification requestSpec, String endPoint, Object body);
	ResponseAPI patch(RequestSpecification requestSpec, String endPoint, Object body);
	ResponseAPI delete(RequestSpecification requestSpec);
	ResponseAPI delete(RequestSpecification requestSpec, String endPoint);

}