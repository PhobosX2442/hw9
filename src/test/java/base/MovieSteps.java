package base;

import api.client.MovieClient;
import api.dto.MovieRequest;
import api.dto.MovieResponse;
import io.qameta.allure.Step;

public class MovieSteps {

    @Step("Создаём фильм")
    public static MovieResponse createAndGetMovie(String token) {
        MovieRequest movie = MovieFactory.createMovie();
        return MovieClient.createMovie(movie, token);
    }

    @Step("Забираем id из созданного фильма")
    public static Integer getIdFromCreatedMovie(String token) {
        return createAndGetMovie(token).getId();
    }
}