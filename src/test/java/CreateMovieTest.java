import api.client.MovieClient;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import api.spec.Randomizer;
import db.domain.Movie;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Домашка 9")
@Feature("Создание фильма")
public class CreateMovieTest extends ApiTestBase {
    String token = ApiTestBase.loginAndGetToken();
    int id = 6666;
    int price = Randomizer.getRandomInt();

    @Story("Создание фильма с валидными данными")
    @DisplayName("Создание фильма с валидными данными")
    @Test
    public void createMovie() {
        MovieRequest movie = MovieFactory.createMovie(id);

        MovieClient.createMovie(movie,token);

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, notNullValue());
        assertThat(getMovieSql.getId(), equalTo(id));

        MovieClient.deleteMovie(id,token);
    }

    @Story("Создание фильма без данных")
    @DisplayName("Создание фильма без данных")
    @Test
    public void createMovie404() {
        MovieRequest movie = MovieFactory.createMovie404(price);
        MovieClient.createMovie404(movie,token);

        Movie getMovieSql = dbSteps.getMovieByPrice(price);
        assertThat(getMovieSql, nullValue());
    }
}
