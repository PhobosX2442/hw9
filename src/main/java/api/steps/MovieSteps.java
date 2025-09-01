package api.steps;

import api.client.MovieClient;
import api.dto.MovieRequest;
import api.dto.MovieResponse;
import io.qameta.allure.Step;

public class MovieSteps {

    @Step("Создаём фильм")
    public static MovieResponse createMovie(MovieRequest request, String token) {
        return MovieClient.createMovie(request, token);
    }

}