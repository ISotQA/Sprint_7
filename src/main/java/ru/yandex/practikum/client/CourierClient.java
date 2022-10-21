package ru.yandex.practikum.client;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practikum.dto.CourierRequest;
import ru.yandex.practikum.dto.LoginRequest;

import static io.restassured.RestAssured.given;


public class CourierClient extends RestClient {

    private static final String COURIER = "courier";
    private static final String COURIER_LOGIN = "courier/login";
    private static final String DELETE_COURIER = "courier/";

    public ValidatableResponse createCourier(CourierRequest courierRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(courierRequest)
                .post(COURIER)
                .then();

    }

    public ValidatableResponse loginCourier(LoginRequest loginRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(loginRequest)
                .post(COURIER_LOGIN)
                .then();
    }

    public ValidatableResponse deleteCourier(Integer id) {
        return given()
                .spec(getDefaultRequestSpec())
                .delete(DELETE_COURIER + id)
                .then();
    }

}
