package api.spec;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestSpecificationFactory {

    public static RequestSpecification requestApi() {
        return given()
                .contentType("application/json")
                .baseUri("https://api.cinescope.krisqa.ru");
    }

    public static RequestSpecification requestAuth() {
        return given()
                .contentType("application/json")
                .baseUri("https://auth.cinescope.krisqa.ru");
    }
}


