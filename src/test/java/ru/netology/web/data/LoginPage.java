package ru.netology.web.data;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

@Value
public class LoginPage {
    SelenideElement loginField = $("[data-test-id=login] .input__control");
    SelenideElement passwordField = $("[data-test-id=password] .input__control");
    SelenideElement sentButton = $("[data-test-id=action-login] .button__text");
    SelenideElement successNotification = $(".heading_theme_alfa-on-white");
    SelenideElement notification = $("[data-test-id=error-notification] .notification__content");
    SelenideElement inputPasswordError = $("[data-test-id=password] .input__sub");
    SelenideElement inputLoginError = $("[data-test-id=login] .input__sub");

    public void setData(UserData userData) {
        getLoginField().setValue(userData.getLogin());
        getPasswordField().setValue(userData.getPassword());
    }

    public void clearField(SelenideElement field) {
        field.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
    }

}


