package helpers;

import api.AuthorizationApi;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        LoginResponseModel response = AuthorizationApi.login();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
    }
}