package api.tests;

import api.pojos.AuthRequest;
import api.pojos.Booking;
import api.pojos.BookingDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import api.utils.ApiUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingApiTests extends BaseTest {
    @Test
    public void getBooking_returns200_whenBookingExists() {

        get(baseURI).
                then().
                statusCode(200)
                .body("firstname", notNullValue());
    }

    @Test
    public void postAuthToken_validCredentials_returnsToken() {
        AuthRequest request = new AuthRequest("admin", "password123");

        given()
                    .baseUri(baseURI)
                    .contentType(ContentType.JSON)
                    .body(request)
                    .log().ifValidationFails()
                .when()
                    .post("/auth")
                .then()
                    .log().ifValidationFails()
                    .assertThat()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("token", notNullValue());
    }

    @Test
    public void postAuthToken_invalidCredentials_rejected() {
        AuthRequest request = new AuthRequest("dan", "invalidUser");

        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .body(request)
                .log().everything()
                .when()
                .post("/auth")
                .then()
                .log().everything()
                .assertThat()
                .statusCode(200)
                .body("reason", equalToIgnoringCase("Bad credentials"),
                        "token", nullValue());
    }

    @Test
    public void postBooking_validData_returnsBooking() {
        BookingDate bookingDate = new BookingDate("2018-01-01", "2018-01-03");
        Booking booking = new Booking(
               "Dan",
                "Garcia",
                150,
                false,
                bookingDate,
                "Breakfast"
        );

        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .body(booking)
                .log().ifValidationFails()
                .when()
                .post("/booking")
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", equalTo(booking.getFirstname()),
                        "booking.lastname", equalTo(booking.getLastname()),
                        "booking.totalprice", equalTo(booking.getTotalprice()),
                        "booking.depositpaid", equalTo(booking.isDepositpaid()),
                        "booking.bookingdates.checkin", equalTo(booking.getBookingdates().getCheckin()),
                        "booking.bookingdates.checkout", equalTo(booking.getBookingdates().getCheckout()),
                        "booking.additionalneeds", equalTo(booking.getAdditionalneeds()),
                        "bookingid", notNullValue());
    }

    @Test
    public void getBooking_validData_returnsBooking() {
        BookingDate bookingDate = new BookingDate("2018-01-01", "2018-01-03");
        Booking booking = new Booking(
                "Dan",
                "Garcia",
                150,
                false,
                bookingDate,
                "Breakfast"
        );

        String generatedBookingId = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("bookingid").toString();


        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .get("/booking/" + generatedBookingId)
                .then()
                .log().ifValidationFails()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("firstname", equalTo(booking.getFirstname()),
                        "lastname", equalTo(booking.getLastname()),
                        "totalprice", equalTo(booking.getTotalprice()),
                        "depositpaid", equalTo(booking.isDepositpaid()),
                        "bookingdates.checkin", equalTo(booking.getBookingdates().getCheckin()),
                        "bookingdates.checkout", equalTo(booking.getBookingdates().getCheckout()),
                        "additionalneeds", equalTo(booking.getAdditionalneeds()));
    }

    @Test
    public void getBooking_invalidData_returns404() {
        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .get("/booking/9999")
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void putBooking_validData_validAuth_returnsUpdatedBooking() {
        String authToken = ApiUtils.getAuthToken();

        BookingDate bookingDate = new BookingDate("2018-01-01", "2018-01-03");
        Booking booking = new Booking(
                "Update",
                "Me",
                12,
                false,
                bookingDate,
                "None"
        );

        String createdBookingId = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("bookingid").toString();

        //Update the booking obj
        bookingDate.setCheckin("2026-01-01");
        bookingDate.setCheckout("2026-01-01");
        booking.setFirstname("Eric");
        booking.setLastname("Hobsbawm");
        booking.setTotalprice(1792);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Coffee");
        booking.setBookingdates(bookingDate);

        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .cookie("token", authToken)
                .log().ifValidationFails()
                .body(booking)
                .when()
                .put("/booking/" + createdBookingId)
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(200)
                .body("firstname", equalTo(booking.getFirstname()),
                        "lastname", equalTo(booking.getLastname()),
                        "totalprice", equalTo(booking.getTotalprice()),
                        "depositpaid", equalTo(booking.isDepositpaid()),
                        "bookingdates.checkin", equalTo(booking.getBookingdates().getCheckin()),
                        "bookingdates.checkout", equalTo(booking.getBookingdates().getCheckout()),
                        "additionalneeds", equalTo(booking.getAdditionalneeds()));
    }

    @Test
    public void patchBooking_validData_validAuth_returnsUpdatedBooking() {
        String authToken = ApiUtils.getAuthToken();
        BookingDate bookingDate = new BookingDate("2018-01-01", "2018-01-03");
        Booking booking = new Booking(
                "John",
                "Williams",
                175,
                true,
                bookingDate,
                "None"
        );

        String bookingId = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .path("bookingid").toString();

        String newFirstName = "Michael";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode updatedUser = mapper.createObjectNode();
        updatedUser.put("firstname", newFirstName);
        updatedUser.put("depositpaid", false);

        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .cookie("token", authToken)
                .log().ifValidationFails()
                .body(updatedUser)
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(200)
                .body("firstname", equalTo(newFirstName),
                        "lastname", equalTo(booking.getLastname()),
                        "totalprice", equalTo(booking.getTotalprice()),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo(booking.getBookingdates().getCheckin()),
                        "bookingdates.checkout", equalTo(booking.getBookingdates().getCheckout()),
                        "additionalneeds", equalTo(booking.getAdditionalneeds()));

    }

    @Test
    public void deleteBooking_validAuth_returns201() {
        String authToken = ApiUtils.getAuthToken();
        BookingDate bookingDate = new BookingDate("2018-01-01", "2018-01-03");
        Booking booking = new Booking(
                "Oda",
                "Nobunaga",
                200,
                true,
                bookingDate,
                "Tea"
        );
        
        String bookingId = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("bookingid").toString();

        given()
                .baseUri(baseURI)
                .cookie("token", authToken)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .delete("booking/" + bookingId)
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .assertThat()
                .statusCode(404);



    }
}
