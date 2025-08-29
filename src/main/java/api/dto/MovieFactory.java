package api.dto;

public class MovieFactory {

    private static String name = "Новый фильм про код";
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
