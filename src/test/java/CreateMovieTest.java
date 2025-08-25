import api.client.MovieClient;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import api.dto.UpdateDto;
import api.spec.Randomizer;
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
import static org.hamcrest.Matchers.*;

@Epic("Домашка 9")
@Feature("Создание фильма")
public class CreateMovieTest extends ApiTestBase {
    int id = 6970;

    @Story("Создание фильма")
    @DisplayName("Создание фильма")
    @Test
    public void createMovie() {
        String token = ApiTestBase.loginAndGetToken();
        MovieRequest movie = MovieFactory.createMovie();

        MovieClient.createMovie(movie,token);

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, notNullValue());
        assertThat(getMovieSql.getId(), equalTo(id));
    }

    @Story("Создание фильма без данных")
    @DisplayName("Создание фильма без данных")
    @Test
    public void createMovie400() {
        int price = Randomizer.getRandomInt();

        String token = ApiTestBase.loginAndGetToken();
        UpdateDto update = new UpdateDto(price);

        given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(update)
                .when()
                .post("/movies")
                .then()
                .spec(ResponseSpecificationFactory.invalidResponseSpec());

        Movie getMovieSql = dbSteps.getMovieByPrice(price);
        assertThat(getMovieSql, nullValue());
    }
}
