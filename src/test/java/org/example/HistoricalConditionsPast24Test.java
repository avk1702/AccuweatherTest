package org.example;

import org.example.historicalConditionsPast24.HistoricalConditionsPast24;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HistoricalConditionsPast24Test extends AccuweatherAbstractTest{

    @Test
    void getHistoricalConditionsPast24() {

        List<HistoricalConditionsPast24> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+ "/currentconditions/v1/291102/historical/24")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", HistoricalConditionsPast24.class);

        Assertions.assertEquals(24,response.size());
        Assertions.assertEquals(false, response.get(0).getHasPrecipitation());

    }

}

