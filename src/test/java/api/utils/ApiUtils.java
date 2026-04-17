package api.utils;

import api.pojos.AuthRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class ApiUtils {

    public static String getAuthToken() {
        AuthRequest request = new AuthRequest("admin", "password123");

        String authToken = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(request)
                .when()
                .post("/auth")
                .then()
                .log().ifValidationFails()
                .extract()
                .path("token");

        return authToken;
    }
}
