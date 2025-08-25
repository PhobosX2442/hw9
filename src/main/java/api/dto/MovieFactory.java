package api.dto;
import lombok.Getter;

public class MovieFactory {

    @Getter
    private static String name = "Новый фильм про код";
    private static int price = 99999;

        public static MovieRequest createMovie() {
            return MovieRequest.builder()
                .name(name)
                .description("Очень длинное описание, которое точно поясняет, что это фильм созданный автотестом Светослава Петушкевича")
                .genreId(2)
                .price(price)
                .id(6970)
                .location("MSK")
                .published(false)
                .build();
    }
}
