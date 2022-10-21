import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.client.CourierClient;
import ru.yandex.practikum.dto.CourierRequest;
import ru.yandex.practikum.dto.LoginRequest;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.practikum.generator.CourierRequestGenerator.*;
import static ru.yandex.practikum.generator.LoginRequestGenerator.getLoginRequest;


public class CourierCreateTest {

    private CourierClient courierClient;

    private Integer id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.deleteCourier(id)
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("ok", equalTo(true));
        }

    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка создания курьера. Авторизация добавлена чтобы получить id и удалить")
    public void courierShouldBeCreatedTest() {
        CourierRequest randomCourierRequest = getRandomCourierRequest();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));


        LoginRequest loginRequest = getLoginRequest(randomCourierRequest);

        id = courierClient.loginCourier(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка, что успешный запрос возвращает ok: true")
    public void courierShouldBeCreatedIsNullTest() {
        CourierRequest randomCourierRequest = getCourierRequestWithoutFirstName();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));


        LoginRequest loginRequest = getLoginRequest(randomCourierRequest);

        id = courierClient.loginCourier(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка создания курьера с повторяющимся логином")
    public void courierShouldBeCreatedLoginUsedTest() {
        CourierRequest randomCourierRequest = getRandomCourierRequest();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка создания курьера без указания параметра login")
    public void courierShouldBeCreatedWithoutLoginTest() {
        CourierRequest randomCourierRequest = getCourierRequestWithoutLogin();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка создания курьера без указания параметра password")
    public void courierShouldBeCreatedWithoutPasswordTest() {
        CourierRequest randomCourierRequest = getCourierRequestWithoutPassword();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Проверка создания курьера с пустыми атрибутами")
    public void courierShouldBeCreatedAllParamsIsEmptyTest() {
        CourierRequest randomCourierRequest = getCourierRequestAllParamsIsEmpty();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
