package api.tests;

import org.testng.annotations.Test;
import org.hamcrest.Matchers.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

public class BookingApiTests {
    @Test
    public void getBooking_returns200_whenBookingExists() {
        String url = "https://restful-booker.herokuapp.com/booking";
        get(url).
                then().
                statusCode(200)
                .body("firstname", notNullValue());
    }
}
