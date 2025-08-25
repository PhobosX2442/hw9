package api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import db.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieRequest {
    private long id;
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

