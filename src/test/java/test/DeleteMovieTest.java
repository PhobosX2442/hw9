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
import static org.hamcrest.Matchers.nullValue;

@Epic("Домашка 9")
@Feature("Удаление фильма")
@Story("Удаление фильма")
public class DeleteMovieTest extends ApiTestBase {
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
        // Очистка на случай, если тест не удалил фильм сам
        if (createdMovieId != null) {
            MovieClient.deleteMovie(createdMovieId, token);
            createdMovieId = null;
        }
    }

    @Story("Удаление фильма")
    @DisplayName("Удаление фильма")
    @Test
    public void deleteMovie() {
        Integer id = createdMovieId;
        MovieClient.deleteMovie(id, token);
        Movie dbMovie = dbSteps.getMovieById(id);

        Allure.step("Проверяем, что фильм удалён", () -> {
            assertThat(dbMovie, nullValue());
        });

    }

//    @Test
//    public void hardDelete() {
//        int id = 330;
//        MovieClient.deleteMovie(id,token);
//    }
}