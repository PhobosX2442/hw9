package api.steps;

import api.client.MovieClient;
import api.dto.MovieRequest;
import api.dto.MovieResponse;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;

public class MovieSteps {

    @Step("Создаём фильм")
    @Tag("smoke")
    public static MovieResponse createMovie(MovieRequest request, String token) {
        return MovieClient.createMovie(request, token);
    }

    @Step("Удаляем отзыв")
    public static MovieResponse deleteReview(int id, String token) {
        return MovieClient.deleteReview(id, token);
    }
}