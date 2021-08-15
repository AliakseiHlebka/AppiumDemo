package com.epam.java.testing.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class OnlinerStartPage extends BasePage {

    @AndroidFindBy(id = "by.onliner.catalog:id/button_authenticate_password")
    private MobileElement onlinerLogInButton;

    public OnlinerLoginPage openLogInPage() {
        onlinerLogInButton.click();
        return new OnlinerLoginPage();
    }
}
