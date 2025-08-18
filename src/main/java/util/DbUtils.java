package util;

import org.jdbi.v3.core.Jdbi;


public class DbUtils {

    private static final DbCredentials CREDS = DbCredentials.load();

    private DbUtils() {}

    public static Jdbi jdbi() {
        return Jdbi.create(
                CREDS.getUrl(),        // jdbc url
                CREDS.getUsername(),   // username
                CREDS.getPassword()    // password
        );
    }

    public static DbCredentials getCreds(DbName dbMovies) {
        return CREDS;
    }


}
