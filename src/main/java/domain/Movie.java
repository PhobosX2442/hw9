package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Movie {
    private int id;
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private String location;
    private boolean published;
    private int rating;
    private int genreId;
    private String createdAt;
    private String[] reviews;
    private Genre genre;
}