package base;

import api.dto.MovieRequest;
public class MovieFactory {

    public static Integer getRandomInt() {
        return (int) (Math.random() * 100000);
    }
    public static String getRandomName() {
        return "Код, сделанный автотестом " + getRandomInt();
    }

    public static MovieRequest createMovie() {
        return MovieRequest.builder()
                .name(getRandomName())
                .description("Очень длинное описание, которое точно поясняет, что это фильм созданный автотестом Светослава Петушкевича")
                .genreId(2)
                .price(99999)
                .location("MSK")
                .published(false)
                .build();
    }

    public static MovieRequest createMovie404(int setPrice) {
        return MovieRequest.builder()
                .price(setPrice)
                .build();
    }
}