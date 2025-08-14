package api.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CinescopeSpecs {

    public static final RequestSpecification SPEC = given()
            .baseUri("https://api.cinescope.t-qa.ru")
            .contentType("application/json");

    public static ResponseSpecification successCreate() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType("application/json")
                .build();
    }

    public static ResponseSpecification invalidRequest() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectContentType("application/json")
                .build();
    }

}
