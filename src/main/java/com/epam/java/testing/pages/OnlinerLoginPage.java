package com.epam.java.testing.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class OnlinerLoginPage extends BasePage {

    @AndroidFindBy(id = "by.onliner.catalog:id/edit_username")
    private MobileElement usernameTextField;

    @AndroidFindBy(id = "by.onliner.catalog:id/edit_password")
    private MobileElement passwordTextField;

    @AndroidFindBy(id = "by.onliner.catalog:id/button_authenticate")
    private MobileElement signInButton;

    @AndroidFindBy(id = "by.onliner.catalog:id/snackbar_text")
    private MobileElement authenticationStatusMessage;

    public MobileElement getAuthenticationStatusMessage() {
        return authenticationStatusMessage;
    }

    public OnlinerLoginPage enterUsername(String username) {
        usernameTextField.sendKeys(username);
        return this;
    }

    public OnlinerLoginPage enterPassword(String password) {
        passwordTextField.sendKeys(password);
        return this;
    }

    public void signInToOnliner() {
        signInButton.click();
    }
}
