package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class CreateMockingForNewBooking {
	
	public static void main(String[] args) {
		
		String requestBody = """
				{
				  "firstname": "Mark",
				  "lastname": "Brown", 
				  "totalprice": 111, 
				  "depositpaid": true, 
				  "bookingdates": {
				     "checkin": "2018-01-01",
				     "checkout": "2019-01-01"
				  },
				  "additionalneeds": "Breakfast"
				}
				""";
		
		String responseBody = """
				  {
				    "bookingid": 204, 
				    "booking": {
				       "firstname": "Mark",
				       "lastname": "Brown", 
				       "totalprice": 111, 
				       "depositpaid": true, 
				       "bookingdates": {
				          "checkin": "2018-01-01",
				          "checkout": "2019-01-01"
				       },
				    "additionalneeds": "Breakfast"
				    }
				  }
				""";
		
		
		MappingBuilder requestMocking = WireMock.post("/booking")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(requestBody));
		
		
		
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse().withStatus(200)
		        .withStatusMessage("OK")
		        .withBody(responseBody)
		        .withHeader("Content-Type", "application/json");
		
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
		
		
	}

}