import api.client.MovieClient;
import api.dto.UpdateDto;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import db.domain.Movie;
import db.steps.MovieDbSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DbName;
import util.DbUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateMovieTest {
    int id = 40;
    int price = 123456789;

    private MovieDbSteps dbSteps;
    String token = ApiTestBase.loginAndGetToken();

    @BeforeEach
    void setUp() {
        dbSteps = new MovieDbSteps(DbUtils.getCredentials(DbName.DB_MOVIES));
    }

    @Test
    void updateMovie() {
        UpdateDto update = new UpdateDto(price);
        // API часть
        given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(update)
                .when()
                .patch("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .body("price", equalTo(price));

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, notNullValue());
        assertThat(getMovieSql.getPrice(), equalTo(price));
    }
}
