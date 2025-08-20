import db.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import db.steps.MovieDbSteps;
import util.DbName;
import util.DbUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetMovieTest {
    int id = 50;

    private MovieDbSteps dbSteps;

    @BeforeEach
    void setUp() {
        dbSteps = new MovieDbSteps(DbUtils.getCredentials(DbName.DB_MOVIES));
    }

    @Test
    void getMovie() {
        // API часть
        given()
                .spec(RequestSpecificationFactory.requestApi())
                .when()
                .get("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .body("name", equalTo("Свинка"));

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, notNullValue());
        assertThat(getMovieSql.getId(), equalTo(id));
    }

}
