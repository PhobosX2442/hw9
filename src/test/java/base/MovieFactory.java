package base;

import api.dto.MovieRequest;
import api.spec.Randomizer;

public class MovieFactory {
    private static Integer randomName = Randomizer.getRandomInt();
    private static String name = "Код, сделанный автотестом " + randomName;
    private static Integer price = 99999;

    public static MovieRequest createMovie() {
        return MovieRequest.builder()
                .name(name)
                .description("Очень длинное описание, которое точно поясняет, что это фильм созданный автотестом Светослава Петушкевича")
                .genreId(2)
                .price(price)
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