package test;

import api.client.MovieClient;
import api.dto.MovieRequest;
import base.ApiTestBase;
import base.MovieFactory;
import api.steps.MovieSteps;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Домашка 9")
@Feature("Обновление фильма")
@Story("Обновление фильма")
public class UpdateMovieTest extends ApiTestBase {
    private Integer id;

    @AfterEach
    @Step("Очишаем БД от созданного фильма")
    void cleanup() {
        if (id != null) {
            MovieClient.deleteMovie(id, token);
            id = null;
        }
    }

    @Story("Обновление фильма")
    @DisplayName("Обновление фильма")
    @Tag("smoke")
    @Test
    void updateMovie() {
        id = MovieSteps.createMovie(MovieFactory.createMovie(), token).getId();

        Integer price = MovieFactory.getRandomInt();
        MovieRequest update = MovieFactory.createMovie404(price);

        // Достать ценник до обновления Price
        Movie dbMovieBefore = dbSteps.getMovieById(id);
        Integer priceBefore = dbMovieBefore.getPrice();
        System.out.println("Цена до обновления: " + priceBefore);

        // Обновить прайс
        MovieClient.updateMovie(id, update, token);

        // Вытащить новый прайс
        Movie dbMovie = dbSteps.getMovieById(id);
        System.out.println("Цена в базе после обновления: " + dbMovie.getPrice());

        Allure.step("Проверяем значение новой цены", () -> {
            assertThat(dbMovie, notNullValue());
            assertThat(dbMovie.getPrice(), equalTo(price));
        });
    }

}