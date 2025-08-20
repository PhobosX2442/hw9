
import db.domain.Movie;
import db.steps.MovieDbSteps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import util.DbName;
import util.DbUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteMovieTest  {
    int id = 6969;

    private MovieDbSteps dbSteps;

    @BeforeEach
    void setUp() {
        dbSteps = new MovieDbSteps(DbUtils.getCredentials(DbName.DB_MOVIES));
    }

    @Test
    public void deleteMovie() {
        String token = ApiTestBase.loginAndGetToken();

        given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/movies/" + id)
                .then()
                .spec(ResponseSpecificationFactory.deleteResponseSpec());

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, nullValue());
    }
}
