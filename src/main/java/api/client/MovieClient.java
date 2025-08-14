package api.client;

import api.dto.MovieRequest;
import api.dto.Response;
import api.spec.CinescopeSpecs;

import static api.spec.CinescopeSpecs.SPEC;
import static io.restassured.RestAssured.given;

public class MovieClient {

    public io.restassured.response.Response createMovie(MovieRequest movieRequest) {
        return given(SPEC)
                .body(movieRequest)
                .when()
                .post("/movies");
    }

    public Response createMovieSuccessfully(MovieRequest movieRequest) {
        io.restassured.response.Response response = createMovie(movieRequest);
        response.then().spec(CinescopeSpecs.successCreate());
        return response.as(Response.class);
    }

    public io.restassured.response.Response getMovie(long id) {
        return given(SPEC)
                .when()
                .get("/movies/{id}", id);
    }

    public io.restassured.response.Response createMovieWithInvalidData(MovieRequest invalidRequest) {
        return given(SPEC)
                .body(invalidRequest)
                .when()
                .post("/movies");
    }


}
