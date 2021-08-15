package com.epam.java.testing.configuration;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AddressConfigurator {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final String ERROR_MESSAGE = "error";
    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfigurator() {

    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) startService(port);
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        appiumDriverLocalService = new AppiumServiceBuilder()
            .withIPAddress(ConfigurationReader.get().appiumAddress())
            .usingPort(port)
            .withArgument(SESSION_OVERRIDE)
            .withArgument(LOG_LEVEL, ERROR_MESSAGE)
            .build();
        appiumDriverLocalService.start();
        LOG.info("Appium server started on port {}", port);
    }

    public static void stopService() {
        appiumDriverLocalService.stop();
        LOG.info("Appium server stopped");
    }
}
