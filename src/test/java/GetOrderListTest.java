import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.client.OrderClient;
import ru.yandex.practikum.dto.OrderRequest;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @DisplayName("Получение списка заказов")
    @Description("Проверка получения списка заказов. Проверка того, что список не пустой и код ответа корректен")
    @Test
    public void orderListMustNotBeEmpty() {
        OrderRequest orderRequest = new OrderRequest();
        orderClient.getOrderList(orderRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());
    }
}
