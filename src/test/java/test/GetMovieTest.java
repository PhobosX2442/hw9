package test;

import api.client.MovieClient;
import base.ApiTestBase;
import base.MovieSteps;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Домашка 9")
@Feature("Получение фильма")
@Story("Получение фильма")
public class GetMovieTest extends ApiTestBase {
    private String token;
    private Integer createdMovieId;

    @BeforeEach
    @Step("Авторизуемся и получаем id фильма")
    void setup() {
        token = loginAndGetToken();
        createdMovieId = MovieSteps.createAndGetMovie(token).getId();
    }

    @AfterEach
    @Step("Очишаем БД от созданного фильма")
    void teardown() {
        // Очистка: удаляем созданный фильм после каждого теста
        if (createdMovieId != null) {
            MovieClient.deleteMovie(createdMovieId, token);
            createdMovieId = null;
        }
    }

    @Test
    @DisplayName("Получение фильма")
    void getMovie() {
        Integer id = createdMovieId;

        Movie dbMovie = dbSteps.getMovieById(id);

        Allure.step("Проверяем, что фильм создан", () -> {
            assertThat(dbMovie, notNullValue());
        });
        Allure.step("Проверяем id", () -> {
            // Дополнительные проверки на соответствие id
            assertThat(dbMovie.getId(), equalTo(id));
        });

        // Остальные проверки можно оставить как в исходном тесте
    }

}