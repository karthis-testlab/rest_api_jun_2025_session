package week6.day1;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class RestFulBookerAPITestwithWireMockServer {
	
	WireMockServer wireMockServer = new WireMockServer();
	
	@BeforeSuite
	public void upServer() {
		wireMockServer.start();
	}
	
	@BeforeClass
	public void createStub() {
		
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
		
		wireMockServer.stubFor(requestMocking.willReturn(responseMocking));
		
	}
	
	@AfterSuite
	public void stopServer() {
		wireMockServer.stop();
	}
	
	@Test
	public void createNewBookingUsingMock() {
		
		BookingDates bookingDates = new BookingDates();
		bookingDates.setCheckin("2018-01-01");
		bookingDates.setCheckout("2019-01-01");
		
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setFirstname("Mark");
		bookingDetails.setLastname("Brown");
		bookingDetails.setTotalprice(111);
		bookingDetails.setDepositpaid(true);
		bookingDetails.setBookingdates(bookingDates);
        bookingDetails.setAdditionalneeds("Breakfast");		
		
		given()
		  .baseUri("http://localhost:8080")
		  .basePath("/booking")
		  .header("Content-Type", "application/json")
		  .log().all()
		  .when()
		  .body(bookingDetails)
		  .post()
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"));
	}
	
	@Test
	public void createNewBookingUsingReal() {
		
		BookingDates bookingDates = new BookingDates();
		bookingDates.setCheckin("2025-07-19");
		bookingDates.setCheckout("2025-07-21");
		
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setFirstname("Karth");
		bookingDetails.setLastname("Keyan");
		bookingDetails.setTotalprice(180);
		bookingDetails.setDepositpaid(true);
		bookingDetails.setBookingdates(bookingDates);
        bookingDetails.setAdditionalneeds("Breakfast");		
		
		given()
		  .baseUri("https://restful-booker.herokuapp.com")
		  .basePath("/booking")
		  .header("Content-Type", "application/json")
		  .log().all()
		  .when()
		  .body(bookingDetails)
		  .post()
		  .then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"));
	}

}