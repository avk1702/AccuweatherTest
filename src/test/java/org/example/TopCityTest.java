package org.example;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование API https://developer.accuweather.com/apis ")
@Feature("Домашняя работа 6")

public class TopCityTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Текущие условия для Top Cities")
    @Description("Returns current conditions data for the top 50, 100, or 150 cities worldwide, based on rank")
    @Link("https://developer.accuweather.com/accuweather-current-conditions-api/apis")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("avk1702")
    @Story("Тестирование метода getTopCity")
    void getTopCity() {

        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/currentconditions/v1/topcities/50")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l));

    }

}

