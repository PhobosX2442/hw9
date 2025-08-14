package tests;


import api.dto.MovieRequest;
import api.dto.Response;
import api.steps.MovieSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieCreationTest {

    private final MovieSteps movieSteps = new MovieSteps();

    @Test
    @DisplayName("Успешное создание фильма")
    public void successfulMovieCreationTest() {

        MovieRequest requestDto = new MovieRequest();
        requestDto.setId(999);
        requestDto.setName("Авто созданный фильм");

        Response responseDto = movieSteps.createAndGetMovie(requestDto);

        assertThat(responseDto.getName()).isEqualTo(requestDto.getName());
        assertThat(responseDto.getId()).isNotNull();
    }

}
