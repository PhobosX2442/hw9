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
import static org.hamcrest.Matchers.nullValue;

@Epic("Домашка 9")
@Feature("Удаление фильма")
@Story("Удаление фильма")
public class DeleteMovieTest extends ApiTestBase {
    private Integer id;

    @AfterEach
    @Step("Очишаем БД от созданного фильма")
    void teardown() {
        if (id != null) {
            MovieClient.deleteMovie(id, token);
            id = null;
        }
    }

    @Story("Удаление фильма")
    @DisplayName("Удаление фильма")
    @Test
    public void deleteMovie() {
        id = MovieSteps.createMovie(MovieFactory.createMovie(), token).getId();
        MovieClient.deleteMovie(id, token);
        Movie dbMovie = dbSteps.getMovieById(id);

        Allure.step("Проверяем, что фильм удалён", () -> {
            assertThat(dbMovie, nullValue());
        });

    }

//    @Test
//    public void hardDelete() {
//        int id = 356;
//        MovieClient.deleteMovie(id,token);
//    }
}