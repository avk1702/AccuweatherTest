package org.example;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.currentconditions.Currentcondition;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CurrentconditionTest extends AccuweatherAbstractTest{

    @Test
    void getCurrentconditionTyumen() {

        List<Currentcondition> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+ "/currentconditions/v1/291102")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Currentcondition.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(null, response.get(0).getPrecipitationType());

    }

}
