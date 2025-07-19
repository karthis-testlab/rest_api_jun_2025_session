package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreateFirstWireMockStub {

	public static void main(String[] args) {
		
		// http://localhost:8080/greetings
		
		//Request Mocking
		MappingBuilder requestMocking = WireMock.get("/greetings");
		
		// Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withBody("Hi! Welcome to WireMock");
		
		
		// Create Wiremock Stub
		WireMock.stubFor(requestMocking.willReturn(responseMocking));		

	}

}