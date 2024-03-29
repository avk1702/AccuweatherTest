package org.example;

import io.qameta.allure.*;
import org.example.historicalConditionsPast24.HistoricalConditionsPast24;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование API https://developer.accuweather.com/apis ")
@Feature("Домашняя работа 6")

public class HistoricalConditionsPast24Test extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Текущие условия за последние 24 часа")
    @Description("Возвращает исторические данные о текущих условиях за 24 часа")
    @Link("https://developer.accuweather.com/accuweather-current-conditions-api/apis")
    @Severity(SeverityLevel.MINOR)
    @Owner("avk1702")
    @Story("Тестирование метода HistoricalConditionsPast24")
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

