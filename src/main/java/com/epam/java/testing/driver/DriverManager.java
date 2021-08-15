package com.epam.java.testing.driver;

import static java.lang.String.format;

import com.epam.java.testing.configuration.AddressConfigurator;
import com.epam.java.testing.configuration.CapabilitiesConfigurator;
import com.epam.java.testing.configuration.ConfigurationReader;
import com.epam.java.testing.configuration.EnvironmentType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType
        .valueOf(ConfigurationReader.get().environment().toUpperCase());
    private static AppiumDriver<MobileElement> driver;

    private DriverManager() {

    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static AppiumDriver<MobileElement> createDriver() {
        if (ENVIRONMENT_TYPE == EnvironmentType.LOCAL) {
            driver = new AndroidDriver<>(AddressConfigurator
                .getAppiumDriverLocalService(ConfigurationReader.get().appiumPort()),
                CapabilitiesConfigurator.getLocalCapabilities());
        } else {
            throw new IllegalArgumentException(format("Unexpected environment value: %s", ENVIRONMENT_TYPE));
        }
        LOG.info("Driver is created");
        LOG.info("Environment type is {}", ENVIRONMENT_TYPE);
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
        LOG.info("Driver is closed");
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.get().udid()));
            LOG.info("Emulator is closed");
        } catch (IOException e) {
            LOG.error("Emulator was not closed, message: {}", e.getMessage());
        }
    }
}
