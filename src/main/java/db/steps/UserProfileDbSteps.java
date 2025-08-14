package db.steps;

import util.DbName;
import util.DbUtils;

public class UserProfileDbSteps extends DbBaseSteps {
    public UserProfileDbSteps() {
        super(DbUtils.getCredentials(DbName.DB_MOVIES));
    }
}

