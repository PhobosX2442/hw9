package base;

import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import db.steps.MovieDbSteps;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import util.DbName;
import util.DbUtils;

public abstract class ApiTestBase {

    static final String BASE_URI = "https://api.cinescope.krisqa.ru";

    protected String token;
    protected MovieDbSteps dbSteps;

    static {
        RestAssured.baseURI = BASE_URI;
    }

    @BeforeEach
    public void initTokenAndSteps() {
        token = loginAndGetToken();
        dbSteps = new MovieDbSteps(DbUtils.getCredentials(DbName.DB_MOVIES));
    }

    @Step("Авторизация")
    protected static String loginAndGetToken() {
        String loginPayload = "{\"email\":\"test-admin@mail.com\",\"password\":\"KcLMmxkJMjBD1\"}";
        return RestAssured.given()
                .spec(RequestSpecificationFactory.requestAuth())
                .body(loginPayload)
                .when()
                .post("/login")
                .then()
                .spec(ResponseSpecificationFactory.responseSpecification200())
                .extract()
                .path("accessToken");
    }

}