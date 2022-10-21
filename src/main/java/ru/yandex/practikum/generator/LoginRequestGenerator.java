package ru.yandex.practikum.generator;

import ru.yandex.practikum.dto.CourierRequest;
import ru.yandex.practikum.dto.LoginRequest;

public class LoginRequestGenerator {
    public static LoginRequest getLoginRequest(CourierRequest courierRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(courierRequest.getLogin());
        loginRequest.setPassword(courierRequest.getPassword());
        return loginRequest;
    }
}
