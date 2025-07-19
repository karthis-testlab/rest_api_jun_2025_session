package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreateWireMockStubJsonRes {

	public static void main(String[] args) {
		
		// http://localhost:8080/greetings/json
		
		String jsonBody = """
				{
				  "message": "Hi! Welcome to Wiremock"
				}				
				""";
		
		//Request Mocking
		MappingBuilder requestMocking = WireMock.get("/greetings/json");
		
		// Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withBody(jsonBody)
		        .withHeader("Content-Type", "application/json");
		
		
		// Create Wiremock Stub
		WireMock.stubFor(requestMocking.willReturn(responseMocking));		

	}

}