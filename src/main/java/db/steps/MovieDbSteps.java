package db.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.jackson2.Jackson2Config;
import org.jdbi.v3.jackson2.Jackson2Plugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import util.DbCredentials;


public abstract class MovieDbSteps {

    protected Jdbi dbClient;

    public MovieDbSteps(String url, String username, String password) {
        dbClient = buildDbClient(url, username, password);
        initMapper();
    }


    public MovieDbSteps(DbCredentials creds) {
        this(
                "jdbc:postgresql://" + creds.getHost() + ":" + creds.getPort() + "/" + creds.getDbName(),
                creds.getUsername(),
                creds.getPassword()
        );
    }

    private Jdbi buildDbClient(String url, String username, String password) {
        Jdbi jdbi = Jdbi.create(url, username, password);
        jdbi.installPlugin(new PostgresPlugin());
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new Jackson2Plugin());
        return jdbi;
    }

    private void initMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.registerModule(new JavaTimeModule());
        dbClient.getConfig(Jackson2Config.class).setMapper(mapper);
    }
}
