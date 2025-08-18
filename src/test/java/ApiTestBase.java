import io.restassured.RestAssured;
import org.jdbi.v3.core.Jdbi;
import spec.RequestSpecificationFactory;
import spec.ResponseSpecificationFactory;

import java.time.Duration;

public abstract class ApiTestBase {


    static final String BASE_URI = "https://api.cinescope.krisqa.ru";
    protected Jdbi dbClient;

    static {
        RestAssured.baseURI = BASE_URI;
    }

    protected static String loginAndGetToken() {

        String loginPayload = "{\"email\":\"test-admin@mail.com\",\"password\":\"KcLMmxkJMjBD1\"}";

        return RestAssured.given()
                .spec(RequestSpecificationFactory.requestAuth())
                .body(loginPayload)
                .when()
                .post("/login")
                .then()
                .log().all()
                .spec(ResponseSpecificationFactory.getResponseSpec())
                .extract()
                .path("accessToken");
    }
}
