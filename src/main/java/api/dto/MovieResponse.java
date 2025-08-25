package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import db.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {
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
    //фантомный код
    private String message;
    private String error;
    private String statusCode;
}