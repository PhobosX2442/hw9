package api.client;

import db.domain.Movie;
import lombok.Getter;

public class MovieClient {

    @Getter
    private static String name = "Новый фильм про код";
    private static int price = 99999;

    public static Movie createMovie() {
        return Movie.builder()
                .name(name)
                .description("Очень длинное описание, которое точно поясняет, что это фильм созданный автотестом Светослава Петушкевича")
                .genreId(2)
                .price(price)
                .id(6969)
                .location("MSK")
                .published(false)
                .build();
    }

    public static Movie createMovieForDelete() {
        return Movie.builder()
                .name("Фильм для удаления")
                .description("Фильм для удаления")
                .genreId(2)
                .price(price)
                .id(404404)
                .location("MSK")
                .published(false)
                .build();
    }
}


