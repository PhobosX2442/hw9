package Tests;

import api.client.MovieClient;
import base.ApiTestBase;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import api.spec.Randomizer;
import api.steps.MovieSteps;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Домашка 9")
@Feature("Создание фильма")
@Story("Создание фильма")
public class CreateMovieTest extends ApiTestBase {
    private String token = ApiTestBase.loginAndGetToken();
    private Integer price = Randomizer.getRandomInt();

    @Story("Создание фильма с валидными данными")
    @DisplayName("Создание фильма с валидными данными")
    @Description("Вводим все данные")
    @Test
    public void createMovie() {
        createdMovieId = MovieSteps.getIdFromCreatedMovie(token);
        Integer id = createdMovieId;

        Movie getMovieSql = dbSteps.getMovieById(id);
        Allure.step("Проверяем, что фильм создался", () -> {
            assertThat(getMovieSql, notNullValue());
            assertThat(getMovieSql.getId(), equalTo(id));
        });

    }

    @Story("Создание фильма без данных")
    @DisplayName("Создание фильма без данных")
    @Description("Вводим только ценник")
    @Test
    public void createMovie404() {
        createdMovieId = MovieSteps.getIdFromCreatedMovie(token);
        MovieRequest movie = MovieFactory.createMovie404(price);
        MovieClient.createMovie404(movie,token);

        Movie dbMovie = dbSteps.getMovieByPrice(price);

        Allure.step("Проверяем, что фильм не был создан", () -> {
            assertThat(dbMovie, nullValue());
        });

    }
}
