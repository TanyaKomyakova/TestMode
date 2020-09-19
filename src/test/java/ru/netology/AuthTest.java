package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.form;
import static io.restassured.RestAssured.given;
import static ru.netology.DataGenerator.Registration.*;

public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void checkingTheRegisteredUser() {
        Client validClient = generateValidClient();
        $("[data-test-id=login] input").setValue(validClient.getLogin());
        $("[data-test-id=password] input").setValue(validClient.getPassword());
        $("button[data-test-id=action-login]").click();
        $(".App_appContainer__3jRx1 h2.heading").shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    @Test
    void checkingABlockedUser() {
        Client blockedClient = generateBlockedClient();
        $("[data-test-id=login] input").setValue(blockedClient.getLogin());
        $("[data-test-id=password] input").setValue(blockedClient.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Ошибка"));
    }

    @Test
    void checkInvalidClientLogin() {
        Client invalidClientLogin = generateInvalidClientLogin();
        $("[data-test-id=login] input").setValue(invalidClientLogin.getLogin());
        $("[data-test-id=password] input").setValue(invalidClientLogin.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Ошибка"));

    }

    @Test
    void checkInvalidClientPassword() {
        Client invalidClientPassword = generateInvalidClientPassword();
        $("[data-test-id=login] input").setValue(invalidClientPassword.getLogin());
        $("[data-test-id=password] input").setValue(invalidClientPassword.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Ошибка"));

    }
}

