import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practikum.client.OrderClient;
import ru.yandex.practikum.dto.OrderRequest;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.yandex.practikum.generator.OrderRequestGenerator.getCreateOrderRequest;

    @RunWith(Parameterized.class)
    public class CreateOrderTest {
        private OrderClient orderClient;
        private String[] color;

        @Before
        public void setUp() {
            orderClient = new OrderClient();
        }


        public CreateOrderTest(String[] color) {
            this.color = color;
        }

        @Parameterized.Parameters (name = "TestData: {0}, {1}, {2}, {3}")
        public static Object[][] getColor() {
            return new Object[][]{
                    {new String[]{""}},
                    {new String[]{"BLACK"}},
                    {new String[]{"GRAY"}},
                    {new String[]{"BLACK", "GRAY"}}
            };

        }

        @Test
        @DisplayName("Создание заказа.")
        @Description("Проверка создания заказа. Параметр color пустой. Все тесты позитивные")
        public void createOrderTest() {
            OrderRequest orderRequest = getCreateOrderRequest(color);
            orderClient.createOrder(orderRequest)
                    .assertThat()
                    .statusCode(SC_CREATED)
                    .and()
                    .body("track", notNullValue());
        }
}
