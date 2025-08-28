import api.client.MovieClient;
import api.steps.CreateMovieAndGetId;
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
    String token = ApiTestBase.loginAndGetToken();


    @Story("Удаление фильма")
    @DisplayName("Удаление фильма")
    @Test
    public void deleteMovie() {
        int id = CreateMovieAndGetId.createAndGetMovieId(token);

        MovieClient.deleteMovie(id,token);

        Movie getMovieSql = dbSteps.getMovieById(id);
        assertThat(getMovieSql, nullValue());
    }
}
