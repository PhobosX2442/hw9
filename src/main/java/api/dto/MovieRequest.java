package api.dto;

import db.domain.Genre;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

