package db.steps;

import db.dao.MoviesDao;
import db.domain.Movie;
import util.DbCredentials;

public class MovieDbSteps extends DbBaseSteps {

    public MovieDbSteps(String url, String username, String password) {
        super(url, username, password);
    }

    public MovieDbSteps(DbCredentials creds) {
        super(creds);
    }

    public Movie selectByMovieId(int id) {
        return dbClient.withExtension(MoviesDao.class, dao -> dao.selectByMovieId(id));
    }

}
