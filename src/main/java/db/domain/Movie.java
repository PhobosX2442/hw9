package db.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private String location;
    private boolean published;
    private Integer rating;
    private Integer genreId;
    private String createdAt;
    private String[] reviews;
    private Genre genre;
}