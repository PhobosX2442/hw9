import api.client.MovieClient;
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
public class GetMovieTest extends ApiTestBase {
    int id = 50;

    @Story("Получение фильма")
    @DisplayName("Получение фильма")
    @Test
    void getMovie() {
        MovieClient.getMovie(id);

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, notNullValue());
        assertThat(getMovieSql.getId(), equalTo(id));
    }
}
