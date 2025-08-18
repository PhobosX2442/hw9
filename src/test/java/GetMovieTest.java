import org.junit.jupiter.api.Test;
import spec.RequestSpecificationFactory;
import spec.ResponseSpecificationFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetMovieTest extends ApiTestBase {

    @Test
    void getMovie() {
        given()
                .spec(RequestSpecificationFactory.requestApi())
                .when()
                .get("/movies/5")
                .then()
                .log().all()
                .spec(ResponseSpecificationFactory.getResponseSpec())
                .body("name", equalTo("Море крови и рваных жоп 2"));
    }

}
