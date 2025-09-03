package test;

import api.client.MovieClient;
import api.dto.MovieRequest;
import api.dto.MovieResponse;
import api.spec.Randomizer;
import api.steps.MovieSteps;
import base.ApiTestBase;
import base.MovieFactory;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Домашка 9")
@Feature("Создание фильма")
@Story("Создание фильма")
public class CreateMovieTest extends ApiTestBase {
    private Integer id;

    @AfterEach
    @Step("Очишаем БД от созданного фильма")
    void cleanup() {
        if (id != null) {
            MovieClient.deleteMovie(id, token);
            id = null;
        }
    }

    @Story("Создание фильма с валидными данными")
    @DisplayName("Создание фильма с валидными данными")
    @Description("Вводим все данные")
    @Test
    public void createMovie() {
        MovieRequest request = MovieFactory.createMovie();
        MovieResponse response = MovieSteps.createMovie(request, token);
        id = response.getId();

        Movie getMovieSql = dbSteps.getMovieById(id);
        Allure.step("Проверяем сохранение данных в БД", () -> {
            assertThat(getMovieSql, notNullValue());
            assertThat(request.getName(), equalTo(getMovieSql.getName()));
            assertThat(request.getPrice(), equalTo(getMovieSql.getPrice()));
            assertThat(request.getDescription(), equalTo(getMovieSql.getDescription()));
            assertThat(request.getLocation(), equalTo(getMovieSql.getLocation()));
        });
    }

    @Story("Создание фильма без данных")
    @DisplayName("Создание фильма без данных")
    @Description("Вводим только ценник")
    @Test
    public void createMovie404() {
        MovieRequest movie = MovieFactory.createMovie404(Randomizer.getRandomInt());
        MovieClient.createMovie404(movie, token);

        Movie dbMovie = dbSteps.getMovieByPrice(movie.getPrice());

        Allure.step("Проверяем, что фильм не был создан (или не нарушает условие)", () -> {
            assertThat(dbMovie, nullValue());
        });

    }

}