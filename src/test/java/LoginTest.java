import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practikum.client.CourierClient;
import ru.yandex.practikum.dto.CourierRequest;
import ru.yandex.practikum.dto.LoginRequest;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.yandex.practikum.generator.CourierRequestGenerator.getRandomCourierRequest;
import static ru.yandex.practikum.generator.LoginRequestGenerator.getLoginRequest;

public class LoginTest {

    private CourierClient courierClient;
    private CourierRequest randomCourierRequest;
    private Integer id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        randomCourierRequest = getRandomCourierRequest();
        courierClient.createCourier(randomCourierRequest)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
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
    @DisplayName("Авторизация курьера.")
    @Description("Проверка авторизации курьера с корректными параметрами авторизации")
    public void courierMustBeAuthorizedTest() {
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
    @DisplayName("Авторизация курьера.")
    @Description("Проверка авторизацими курьера с незаполненным обязательным параметром login")
    public void courierMustBeAuthorizedLoginIsEmptyTest() {
        LoginRequest loginRequest = getLoginRequest(randomCourierRequest);
        loginRequest.setLogin("");
        courierClient.loginCourier(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера.")
    @Description("Проверка авторизацими курьера с незаполненным обязательным параметром password")
    public void courierMustBeAuthorizedPasswordIsEmptyTest() {
        LoginRequest loginRequest = getLoginRequest(randomCourierRequest);
        loginRequest.setPassword("");
        courierClient.loginCourier(loginRequest)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера.")
    @Description("Проверка авторизацими курьера с логином отличным от имеющегося")
    public void courierMustBeAuthorizedIncorrectLoginTest() {
        LoginRequest loginRequest = getLoginRequest(randomCourierRequest);
        loginRequest.setLogin("test");
        courierClient.loginCourier(loginRequest)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера.")
    @Description("Проверка авторизацими курьера с паролем отличным от имеющегося")
    public void courierMustBeAuthorizedIncorrectPasswordTest() {
        LoginRequest loginRequest = getLoginRequest(randomCourierRequest);
        loginRequest.setPassword("test");
        courierClient.loginCourier(loginRequest)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
