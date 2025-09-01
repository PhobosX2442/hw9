package db.steps;

import db.dao.MovieDao;
import db.domain.Movie;
import io.qameta.allure.Step;
import util.DbCredentials;

public class MovieDbSteps extends DbBaseSteps {
    public MovieDbSteps(DbCredentials creds) {
        super(creds);
    }

    @Step("Получаем фильма из БД по id")
    public Movie getMovieById(int id) {
        return dbClient.withExtension(MovieDao.class, dao -> dao.selectById(id));
    }

    @Step("Получаем фильма из БД по ценнику")
    public Movie getMovieByPrice(int price) {
        return dbClient.withExtension(MovieDao.class, dao -> dao.selectByPrice(price));
    }

}
