package com.matschie.service.now.hooks;

import static io.restassured.RestAssured.basic;

import org.testng.annotations.BeforeClass;

import com.matschie.api.design.ResponseAPI;
import com.matschie.service.now.services.IncidentService;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseClass extends IncidentService {
	
	protected String sysId;
    protected ResponseAPI response;	
    protected RequestSpecification requestSpec;
	// Set the all common values
    RequestSpecBuilder builder = new RequestSpecBuilder();
    
    protected String update_requestPayload = """
    		{
             "short_description": "REST Update using Put Method",
             "description": "Update existing record from file",
             "active": "true"
            }    		
    		""";
	
	@BeforeClass
	public void setUp() {
		requestSpec = builder
		.setBaseUri("https://dev265761.service-now.com")
		.setBasePath("/api/now/table/{tableName}")
		.setAuth(basic("admin", "d@9IvhOh5DR*"))
		.addPathParam("tableName", "incident")	
		.build(); // Convert RequestSpecBuilder object to RequestSpecification object
	}

}