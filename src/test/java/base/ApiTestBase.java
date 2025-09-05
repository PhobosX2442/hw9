package base;

import api.client.AuthClient;
import db.steps.MovieDbSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import util.DbName;
import util.DbUtils;

public abstract class ApiTestBase {

    static final String BASE_URI = "https://api.cinescope.krisqa.ru";

    protected String token;
    protected String userId;
    protected MovieDbSteps dbSteps;

    static {
        RestAssured.baseURI = BASE_URI;
    }

    @BeforeEach
    public void initTokenAndSteps() {
        Response creds = AuthClient.loginAndGetToken(AuthClient.Role.ADMIN);
        token = creds.path("accessToken");
        userId = creds.path("id");
        dbSteps = new MovieDbSteps(DbUtils.getCredentials(DbName.DB_MOVIES));
    }
}