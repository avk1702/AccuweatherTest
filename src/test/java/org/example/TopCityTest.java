package org.example;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class TopCityTest extends AccuweatherAbstractTest{

    @Test
    void getTopCity() {

        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/currentconditions/v1/topcities/50")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l));

    }

}

