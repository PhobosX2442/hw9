package tests;

import db.steps.MovieDbSteps;
import db.steps.UserProfileDbSteps;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import util.DbCredentials;
import util.DbUtils;

import static util.DbName.DB_MOVIES;

public class UserProfileServiceTest implements BeforeAllCallback {

    private static UserProfileDbSteps dbSteps;
    private static MovieDbSteps movieDbSteps;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        dbSteps = new UserProfileDbSteps();
        DbCredentials creds = DbUtils.getCredentials(DB_MOVIES);
        movieDbSteps = new MovieDbSteps(creds);
    }


}
