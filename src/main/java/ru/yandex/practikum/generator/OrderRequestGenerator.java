package ru.yandex.practikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import ru.yandex.practikum.dto.OrderRequest;

public class OrderRequestGenerator {
    public static OrderRequest getCreateOrderRequest(String[] color) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setFirstName(RandomStringUtils.randomAlphabetic(10));
        orderRequest.setLastName(RandomStringUtils.randomAlphabetic(10));
        orderRequest.setAddress(RandomStringUtils.randomAlphabetic(10));
        orderRequest.setMetroStation(RandomStringUtils.randomAlphabetic(10));
        orderRequest.setPhone("+7 900 624 73 56");
        orderRequest.setRentTime(RandomUtils.nextInt(1, 30));
        orderRequest.setDeliveryDate("1996-08-17");
        orderRequest.setComment(RandomStringUtils.randomAlphabetic(10));
        orderRequest.setColor(color);

        return orderRequest;
    }
}
