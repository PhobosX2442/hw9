package api.steps;

import api.client.MovieClient;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import api.dto.MovieResponse;
import io.qameta.allure.Step;

public class MovieSteps {

    @Step("Создание фильма")
    public static MovieResponse createAndGetMovie(String token) {
        MovieRequest movie = MovieFactory.createMovie();
        return MovieClient.createMovie(movie, token);
    }

    @Step("Взятие id из созданного фильма")
    public static Integer getIdFromCreatedMovie(String token) {
        return createAndGetMovie(token).getId();
    }
}
