package tests;

import db.dao.MoviesDao;
import db.domain.Movie;
import db.steps.DbBaseSteps;
import util.DbCredentials;

public class MovieDbTest extends DbBaseSteps {

    public MovieDbTest(String url, String username, String password) {
        super(url, username, password);
    }

    public MovieDbTest(DbCredentials creds) {
        super(creds);
    }

    public Movie selectByMovieId(int id) {
        return dbClient.withExtension(MoviesDao.class, dao -> dao.selectByMovieId(id));
    }
}
