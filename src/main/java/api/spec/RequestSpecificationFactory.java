package api.spec;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpecificationFactory {

    public static RequestSpecification requestApi() {
        return given()
                .contentType("application/json")
                .baseUri("https://api.cinescope.t-qa.ru");
    }
    public static RequestSpecification requestAuth() {
        return given()
                .contentType("application/json")
                .baseUri("https://auth.cinescope.t-qa.ru");
    }
}


