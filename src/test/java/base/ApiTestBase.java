package base;

import api.client.MovieClient;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import db.steps.MovieDbSteps;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import util.DbName;
import util.DbUtils;

public abstract class ApiTestBase {

    static final String BASE_URI = "https://api.cinescope.krisqa.ru";

    public String getToken() {
        return token;
    }

    private String token = loginAndGetToken();
    protected MovieDbSteps dbSteps;
    protected Integer createdMovieId = null;

    static {
        RestAssured.baseURI = BASE_URI;
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
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .extract()
                .path("accessToken");
    }

    @BeforeEach
    void initDbSteps() {
        dbSteps = new MovieDbSteps(DbUtils.getCredentials(DbName.DB_MOVIES));
    }

    @AfterEach
    @Step("Очистка данных")
    public void cleanup() {
        if (createdMovieId != null) {
            try {
                MovieClient.deleteMovie(createdMovieId, getToken());
            } catch (Exception e) {
                // Можно логировать ошибку, чтобы не мешать выполнению тестов
                System.out.println("Ошибка при удалении фильма: " + e.getMessage());
            }
            createdMovieId = null; // чтобы не удалять повторно
        }
    }
}