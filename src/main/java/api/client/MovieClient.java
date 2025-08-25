package api.client;

import api.dto.MovieRequest;
import api.dto.MovieResponse;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import db.domain.Movie;
import lombok.Getter;

import static io.restassured.RestAssured.given;

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
//    public static Movie createMovieForDelete() {
//        return Movie.builder()
//                .name("Фильм для удаления")
//                .description("Фильм для удаления")
//                .genreId(2)
//                .price(price)
//                .id(404404)
//                .location("MSK")
//                .published(false)
//                .build();
//    }
}


