package db.steps;

import db.dao.MovieDao;
import db.domain.Movie;
import util.DbCredentials;

public class MovieDbSteps extends DbBaseSteps {
    public MovieDbSteps(DbCredentials creds) {
        super(creds);
    }

    public Movie getMovieById(int id) {
        return dbClient.withExtension(MovieDao.class, dao -> dao.selectById(id));
    }

    public Movie getMovieByPrice(int price) {
        return dbClient.withExtension(MovieDao.class, dao -> dao.selectByPrice(price));
    }

}
