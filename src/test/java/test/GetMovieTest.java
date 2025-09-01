package test;

import api.dto.MovieResponse;
import base.MovieSteps;
import base.ApiTestBase;
import db.domain.Movie;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Домашка 9")
@Feature("Получение фильма")
@Story("Получение фильма")
public class GetMovieTest extends ApiTestBase {
    private String token = ApiTestBase.loginAndGetToken();


    @Story("Получение фильма")
    @DisplayName("Получение фильма")
    @Test
    void getMovie() {
        MovieResponse createdMovie = MovieSteps.createAndGetMovie(token);
        createdMovieId = createdMovie.getId();
        Integer id = createdMovieId;

        Movie dbMovie = dbSteps.getMovieById(id);

        Allure.step("Проверяем, что фильм создан", () -> {
            assertThat(dbMovie, notNullValue());
        });
        Allure.step("Проверяем id", () -> {
            assertThat(createdMovie.getId(), equalTo(dbMovie.getId()));
        });
        Allure.step("Проверяем название", () -> {
            assertThat(createdMovie.getName(), equalTo(dbMovie.getName()));
        });
        Allure.step("Проверяем цену", () -> {
            assertThat(createdMovie.getPrice(), equalTo(dbMovie.getPrice()));
        });
        Allure.step("Проверяем описание", () -> {
            assertThat(createdMovie.getDescription(), equalTo(dbMovie.getDescription()));
        });
        Allure.step("Проверяем локацию", () -> {
            assertThat(createdMovie.getLocation(), equalTo(dbMovie.getLocation()));
        });

    }
}