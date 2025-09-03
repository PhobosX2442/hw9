package test;

import api.client.MovieClient;
import api.steps.MovieSteps;
import base.ApiTestBase;
import base.MovieFactory;
import db.domain.Movie;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@Epic("Домашка 9")
@Feature("Удаление фильма")
@Story("Удаление фильма")
public class DeleteMovieTest extends ApiTestBase {
    private Integer id;

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

}