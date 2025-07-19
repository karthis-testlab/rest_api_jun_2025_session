package week6.day1;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RestFulBookerAPITest {
	
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