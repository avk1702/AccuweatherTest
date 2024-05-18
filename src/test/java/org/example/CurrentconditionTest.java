package org.example;


import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.currentconditions.Currentcondition;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование API https://developer.accuweather.com/apis ")
@Feature("Домашняя работа 6")

public class CurrentconditionTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Текущие условия")
    @Description("Возвращает данные о текущих условиях для определенного местоположения")
    @Link("https://developer.accuweather.com/accuweather-current-conditions-api/apis")
    @Severity(SeverityLevel.NORMAL)
    @Owner("avk1702")
    @Story("Тестирование метода Currentcondition")
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
