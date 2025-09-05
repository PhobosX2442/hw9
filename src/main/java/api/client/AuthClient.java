package api.client;

import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AuthClient {

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        USER("quicksilverx@yandex.ru", "Qwer1234"),
        ADMIN("test-admin@mail.com", "KcLMmxkJMjBD1");

        private final String email;
        private final String password;
    }

    @Step("Авторизация под ролью {role}")
    public static Response loginAndGetToken(Role role) {
        String loginPayload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}",
                role.getEmail(),
                role.getPassword()
        );

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

    @Step("Авторизация (по умолчанию USER)")
    public static Response loginAndGetToken() {
        return loginAndGetToken(Role.USER);
    }

    public static String getAuthToken() {
        return getAuthToken(Role.USER);
    }

    public static String getAuthToken(Role role) {
        Response response = loginAndGetToken(role);
        return response.path("accessToken");
    }
}