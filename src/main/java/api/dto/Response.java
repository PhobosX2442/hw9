package api.dto;

import lombok.Data;

@Data
public class Response {
    private long id;
    private String name;
    private int price;
    private String location;
}
