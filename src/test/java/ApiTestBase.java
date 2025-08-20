import io.restassured.RestAssured;
import api.spec.RequestSpecificationFactory;
import api.spec.ResponseSpecificationFactory;

public abstract class ApiTestBase {


    static final String BASE_URI = "https://api.cinescope.krisqa.ru";

    public String getToken() {
        return token;
    }
    private String token = loginAndGetToken();

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
                .spec(ResponseSpecificationFactory.successResponseSpec())
                .extract()
                .path("accessToken");

    }

}
