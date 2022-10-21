package ru.yandex.practikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practikum.dto.CourierRequest;

public class CourierRequestGenerator {
    public static CourierRequest getRandomCourierRequest() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setFirstName("firstName");
        courierRequest.setPassword("password");
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10 ));
        return courierRequest;
    }

    public static CourierRequest getCourierRequestWithoutFirstName() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setPassword("12345");
        return courierRequest;
    }

    public static CourierRequest getCourierRequestWithoutLogin() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setLogin("");
        courierRequest.setPassword("12345");
        courierRequest.setFirstName("Vasya");
        return courierRequest;
    }

    public static CourierRequest getCourierRequestWithoutPassword() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setPassword("");
        courierRequest.setFirstName("Vasya");
        return courierRequest;
    }

    public static CourierRequest getCourierRequestAllParamsIsEmpty() {
        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setLogin("");
        courierRequest.setPassword("");
        courierRequest.setFirstName("");
        return courierRequest;
    }
}
