package api.steps;

import api.client.MovieClient;
import api.dto.MovieRequest;
import api.dto.Response;
import api.spec.CinescopeSpecs;

public class MovieSteps {

    private final MovieClient movieClient = new MovieClient();

    public Response createAndGetMovie(MovieRequest movieRequest) {
        Response creationResponse = movieClient.createMovieSuccessfully(movieRequest);
        long newMovieId = creationResponse.getId();

        return movieClient.getMovie(newMovieId)
                .then()
                .statusCode(200)
                .extract().body().as(Response.class);
    }

    public io.restassured.response.Response createMovieWithInvalidData(MovieRequest invalidRequest) {
        return (io.restassured.response.Response) movieClient.createMovieWithInvalidData(invalidRequest)
                .then()
                .spec(CinescopeSpecs.invalidRequest());
    }

    public Response getMovieById(long id, String expectedName) {
        Response movie = movieClient.getMovie(id)
                .then()
                .statusCode(200)
                .extract().body().as(Response.class);
        return movie;

    }
}
