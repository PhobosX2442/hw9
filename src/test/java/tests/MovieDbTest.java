package tests;

import api.client.MovieClient;
import db.dao.MoviesDao;
import util.DbCredentials;

public class MovieDbTest {

    public UserServiceDbSteps(String url, String username, String password) {
        super(url, username, password);
    }


    public UserServiceDbSteps(DbCredentials creds) {
        super(creds);
    }


    @Step("get user by id {userId}")
    public User getUserByUserId(long userId) {
        return MovieClient.withExtension(MoviesDao.class, dao -> dao.selectByUserId(userId));
    }

    public List<User> getAllUsers() {
        return MovieClient.withExtension(MoviesDao.class, MoviesDao::selectAll);
    }

    public void insertUser(User user) {
        MovieClient.useExtension(MoviesDao.class, dao -> dao.insertUser(user));
    }

    public void deleteUserById(long userId) {
        // Аналогично, useExtension подходит для void-метода удаления
        MovieClient.useExtension(MoviesDao.class, dao -> dao.deleteUserById(userId));
    }

    // когда у нас появятся еще DAO, мы также добавим сюда шаги для работы с ним
}
