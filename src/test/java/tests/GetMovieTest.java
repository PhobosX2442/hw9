package tests;

import api.dto.Response;
import api.steps.MovieSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetMovieTest {

    private final MovieSteps movieSteps = new MovieSteps();

    @Test
    @DisplayName("Получение фильма")
    public void getMovieTest() {
        long id = 5L;
        String expectedName = "Море крови и рваных жоп 2";

        movieSteps.getMovieById(5, "Море крови и рваных жоп 2");
        Response movie = movieSteps.getMovieById(id, expectedName);


        assertEquals(id, movie.getId(), "ID фильма не совпадает");
        assertEquals(expectedName, movie.getName(), "Название фильма не совпадает");
    }

}
