import api.client.MovieClient;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import api.dto.UpdateRequest;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;
import api.spec.Randomizer;
import db.domain.Movie;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Домашка 9")
@Feature("Обновление фильма")
public class UpdateMovieTest extends ApiTestBase{
    int id = 40;
    int price = Randomizer.getRandomInt();

    @Story("Обновление фильма")
    @DisplayName("Обновление фильма")
    @Test
    void updateMovie() {
        String token = ApiTestBase.loginAndGetToken();
        MovieRequest update = MovieFactory.createMovie404(price);

        MovieClient.updateMovie(id,price,update,token);

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, notNullValue());
        assertThat(getMovieSql.getPrice(), equalTo(price));
    }
}
