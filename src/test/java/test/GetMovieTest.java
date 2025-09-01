package test;

import api.client.MovieClient;
import base.ApiTestBase;
import api.steps.MovieSteps;
import base.MovieFactory;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Домашка 9")
@Feature("Получение фильма")
@Story("Получение фильма")
public class GetMovieTest extends ApiTestBase {
    private Integer id;

    @AfterEach
    @Step("Очишаем БД от созданного фильма")
    void teardown() {
        if (id != null) {
            MovieClient.deleteMovie(id, token);
            id = null;
        }
    }

    @Test
    @DisplayName("Получение фильма")
    void getMovie() {
        id = MovieSteps.createMovie(MovieFactory.createMovie(), token).getId();

        Movie dbMovie = dbSteps.getMovieById(id);

        Allure.step("Проверяем, что фильм создан", () -> {
            assertThat(dbMovie, notNullValue());
        });
        Allure.step("Проверяем id", () -> {
            assertThat(dbMovie.getId(), equalTo(id));
        });
    }

}