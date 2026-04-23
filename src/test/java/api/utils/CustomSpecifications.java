package api.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CustomSpecifications {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .addHeader("Content-Type", "application/json")
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpecificationWithAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .addHeader("Content-Type", "application/json")
                .addCookie("token", ApiUtils.getAuthToken())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecification(ContentType type) {
        return new ResponseSpecBuilder()
                .expectContentType(type)
                .log(LogDetail.ALL)
                .build();
    }
}
