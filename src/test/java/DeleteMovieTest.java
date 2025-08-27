import api.client.MovieClient;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import db.domain.Movie;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Домашка 9")
@Feature("Удаление фильма")
public class DeleteMovieTest extends ApiTestBase {
    int id = 7777;
    String token = ApiTestBase.loginAndGetToken();
    MovieRequest movie = MovieFactory.createMovie(id);

    @Story("Удаление фильма")
    @DisplayName("Удаление фильма")
    @Test
    public void deleteMovie() {

        MovieClient.createMovie(movie,token);
        MovieClient.deleteMovie(id,token);

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, nullValue());
    }
}
