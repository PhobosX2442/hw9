import domain.Genre;
import domain.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spec.RequestSpecificationFactory;
import spec.ResponseSpecificationFactory;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;


public class CreateMovieTest {

    private static String token;

    @BeforeAll
    public static void setup() {
        token = ApiTestBase.loginAndGetToken();
    }

    Movie moviePayload = Movie.builder()
            .name("Новый фильм про код")
            .description("Очень длинное описание, которое точно поясняет, что это фильм созданный автотестом Светослава Петушкевича")
            .genreId(2)
            .price(99999)
            .id(6969)
            .location("MSK")
            .published(false)
            .build();


    @Test
    public void createMovie() {
        given()
                .spec(RequestSpecificationFactory.requestApi())
                .header("Authorization", "Bearer " + token)
                .body(moviePayload)
                .when()
                .post("/movies")
                .then()
                .spec(ResponseSpecificationFactory.createResponseSpec())
                .body("name", equalTo("Новый фильм про код"))
                .body("genre", contains("Комедия"))
                .body("price", equalTo(6969));
    }
}
