package test;

import api.client.MovieClient;
import api.dto.MovieRequest;
import api.spec.Randomizer;
import base.ApiTestBase;
import base.MovieFactory;
import base.MovieSteps;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Домашка 9")
@Feature("Создание фильма")
@Story("Создание фильма")
public class CreateMovieTest extends ApiTestBase {
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

    @Story("Создание фильма с валидными данными")
    @DisplayName("Создание фильма с валидными данными")
    @Description("Вводим все данные")
    @Test
    public void createMovie() {
        Integer id = createdMovieId;

        Movie getMovieSql = dbSteps.getMovieById(id);
        Allure.step("Проверяем, что фильм создан", () -> {
            assertThat(getMovieSql, notNullValue());
            assertThat(getMovieSql.getId(), equalTo(id));
        });

    }

    @Story("Создание фильма без данных")
    @DisplayName("Создание фильма без данных")
    @Description("Вводим только ценник")
    @Test
    public void createMovie404() {
        // В этом тесте предусловие уже создает фильм, но вы можете адаптировать логику под нужды
        MovieRequest movie = MovieFactory.createMovie404(Randomizer.getRandomInt());
        MovieClient.createMovie404(movie, token);

        Movie dbMovie = dbSteps.getMovieByPrice(movie.getPrice());

        Allure.step("Проверяем, что фильм не был создан (или не нарушает условие)", () -> {
            assertThat(dbMovie, nullValue());
        });

    }

}