package tests;

import db.steps.UserProfileDbSteps;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class UserProfileServiceTest implements BeforeAllCallback {

    private static UserProfileDbSteps dbSteps;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        dbSteps = new UserProfileDbSteps();
    }
}
