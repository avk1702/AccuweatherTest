package org.example;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование API https://developer.accuweather.com/apis ")
@Feature("Домашняя работа 6")
public class GetLocationTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Местоположение")
    @Description("Возвращает основную информацию о местоположениях")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("avk1702")
    @Story("Тестирование метода GetLocation")
    void getLocation_search_returnTyumen() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Tyumen")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("291102", response.get(0).getKey());
        Assertions.assertEquals("Tyumen", response.get(0).getLocalizedName());
    }
}