import api.dto.UpdateDto;
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
        UpdateDto update = new UpdateDto(price);

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
