import api.client.MovieClient;
import api.steps.MovieSteps;
import db.domain.Movie;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@Epic("Домашка 9")
@Feature("Удаление фильма")
@Story("Удаление фильма")
public class DeleteMovieTest extends ApiTestBase {
    private String token = ApiTestBase.loginAndGetToken();

    @Story("Удаление фильма")
    @DisplayName("Удаление фильма")
    @Test
    public void deleteMovie() {
        createdMovieId = MovieSteps.getIdFromCreatedMovie(token);
        Integer id = createdMovieId;

        MovieClient.deleteMovie(id,token);

        Movie dbMovie = dbSteps.getMovieById(id);
        assertThat(dbMovie, nullValue());
    }

//    @Story("Принудительное удаление (не трогать без необходимости)")
//    @DisplayName("Принудительное удаление (не трогать без необходимости)")
//    @Test
//    public void hardDelete() {
//        int id = 133;
//
//        MovieClient.deleteMovie(id,token);
//        Movie dbMovie = dbSteps.getMovieById(id);
//        assertThat(dbMovie, nullValue());
//    }
}
