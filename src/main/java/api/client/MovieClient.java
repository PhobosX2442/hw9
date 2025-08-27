package api.client;

import api.dto.MovieRequest;
import api.dto.MovieResponse;
import api.dto.UpdateRequest;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import com.fasterxml.jackson.annotation.JsonInclude;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MovieClient {

    public static MovieResponse createMovie(MovieRequest request, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("/movies")
                .then()
                .spec(ResponseSpecificationFactory.createResponseSpec())
                .extract()
                .as(MovieResponse.class);
    }

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

    public static MovieResponse deleteMovie(int id, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .extract()
                .as(MovieResponse.class);
    }

    public static MovieResponse updateMovie(int id, int price, MovieRequest update, String token) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(update)
                .when()
                .patch("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .body("price", equalTo(price))
                .extract()
                .as(MovieResponse.class);
    }

    public static MovieResponse getMovie(int id) {
        return given()
                .spec(RequestSpecificationFactory.requestApi())
                .when()
                .get("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .extract()
                .as(MovieResponse.class);
    }
}


