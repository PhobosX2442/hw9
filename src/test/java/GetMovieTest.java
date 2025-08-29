import api.client.MovieClient;
import api.dto.MovieResponse;
import api.steps.MovieSteps;
import db.domain.Movie;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;

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

        assertThat(dbMovie, notNullValue());
        assertThat(createdMovie.getId(), equalTo(dbMovie.getId()));
        assertThat(createdMovie.getName(), equalTo(dbMovie.getName()));
        assertThat(createdMovie.getPrice(), equalTo(dbMovie.getPrice()));
        assertThat(createdMovie.getDescription(), equalTo(dbMovie.getDescription()));
        assertThat(createdMovie.getLocation(), equalTo(dbMovie.getLocation()));
    }
}
