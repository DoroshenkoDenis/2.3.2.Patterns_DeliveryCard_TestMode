package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.data.LoginPage;
import ru.netology.web.data.UserData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {
    public LoginPage loginPage = new LoginPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldLogin() {
        UserData userData = DataGenerator.setupAll("ru", "active");
        loginPage.setData(userData);
        loginPage.getSentButton().click();
        loginPage.getSuccessNotification().shouldHave(text("  Личный кабинет"));
    }

    @Test
    public void shouldNotLoginByBlockedUser() {
        UserData userData = DataGenerator.setupAll("ru", "blocked");
        loginPage.setData(userData);
        loginPage.getSentButton().click();
        loginPage.getNotification().shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    public void shouldNotLoginIfBadLogin() {
        UserData userData = DataGenerator.setupAll("ru", "active");
        loginPage.setData(userData);
        loginPage.clearField(loginPage.getLoginField());
        loginPage.getLoginField().setValue("BadLogin");
        loginPage.getSentButton().click();
        loginPage.getNotification().shouldHave(text("Ошибка! " + "\n" + "Неверно указан логин или пароль"));
    }

    @Test
    public void shouldNotLoginIfBadPassword() {
        UserData userData = DataGenerator.setupAll("ru", "active");
        loginPage.setData(userData);
        loginPage.clearField(loginPage.getPasswordField());
        loginPage.getPasswordField().setValue("BadPassword");
        loginPage.getSentButton().click();
        loginPage.getNotification().shouldHave(text("Ошибка! " + "\n" + "Неверно указан логин или пароль"));
    }

    @Test
    public void shouldNotLoginIfNoLogin() {
        UserData userData = DataGenerator.setupAll("ru", "active");
        loginPage.setData(userData);
        loginPage.clearField(loginPage.getLoginField());
        loginPage.getSentButton().click();
        loginPage.getInputLoginError().shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotLoginIfNoPassword() {
        UserData userData = DataGenerator.setupAll("ru", "active");
        loginPage.setData(userData);
        loginPage.clearField(loginPage.getPasswordField());
        loginPage.getSentButton().click();
        loginPage.getInputPasswordError().shouldHave(text("Поле обязательно для заполнения"));
    }

}

