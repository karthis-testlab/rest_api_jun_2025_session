package week4.day2;

import static io.restassured.RestAssured.basic;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	
	protected String sysId;
    protected Response response;	
    protected RequestSpecification requestSpec;
	// Set the all common values
    RequestSpecBuilder builder = new RequestSpecBuilder();
	
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