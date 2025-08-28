package api.steps;

import api.client.MovieClient;
import api.dto.MovieFactory;
import api.dto.MovieRequest;
import api.dto.MovieResponse;

public class CreateMovieAndGetId {
    public static int createAndGetMovieId(String token) {
        MovieRequest movie = MovieFactory.createMovie();
        MovieResponse createdMovie = MovieClient.createMovie(movie, token);
        return createdMovie.getId();
    }

}
