package com.epam.mobile.testing;

import com.epam.java.testing.pages.OnlinerLoginPage;
import com.epam.java.testing.pages.OnlinerStartPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInToOnlinerWithInvalidCredentials extends BaseTest {

    @Test
    public void logInToOnlinerWithInvalidCredentials() {

        String username = "invalid_username";
        String password = "invalid_password";
        String statusMessage = "Проблема с подключением, попробуйте еще раз позже";

        OnlinerLoginPage loginPage = new OnlinerStartPage().openLogInPage();
        loginPage.enterUsername(username)
            .enterPassword(password);
        loginPage.signInToOnliner();

        Assert.assertEquals(loginPage.getAuthenticationStatusMessage().getText(),
            statusMessage);
    }
}
