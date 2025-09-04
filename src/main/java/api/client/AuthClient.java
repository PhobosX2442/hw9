package api.client;

import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthClient {

    @Step("Авторизация")
    public static Response loginAndGetToken() {
        String loginPayload = "{\"email\":\"test-admin@mail.com\",\"password\":\"KcLMmxkJMjBD1\"}";
        return RestAssured.given()
                .spec(RequestSpecificationFactory.requestAuth())
                .body(loginPayload)
                .when()
                .post("/login")
                .then()
                .spec(ResponseSpecificationFactory.responseSpecification200())
                .extract()
                .response();
    }

    public static String getAuthToken() {
        Response response = loginAndGetToken();
        return response.path("accessToken");
    }

}
