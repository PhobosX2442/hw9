package api.client;

import api.dto.MovieRequest;
import api.dto.MovieResponse;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import io.qameta.allure.Step;


import static io.restassured.RestAssured.given;

public class MovieClient {

    @Step("Отправка POST-запроса (все данные)")
    public static MovieResponse createMovie(MovieRequest request, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/movies")
                .then()
                .log().all()
                .spec(ResponseSpecificationFactory.createResponseSpec())
                .extract()
                .as(MovieResponse.class);
    }

    @Step("Отправка POST-запроса (только price)")
    public static MovieResponse createMovie404(MovieRequest request, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/movies")
                .then()
                .spec(ResponseSpecificationFactory.invalidResponseSpec())
                .extract()
                .as(MovieResponse.class);
    }

    @Step("Отправка DELETE запроса")
    public static MovieResponse deleteMovie(int id, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/movies/" + id)
                .then()
                .extract()
                .as(MovieResponse.class);
    }

    @Step("Отправка PATCH-запроса")
    public static MovieResponse updateMovie(int id, int price, MovieRequest update, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(update)
                .when()
                .patch("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .extract()
                .as(MovieResponse.class);
    }
}


