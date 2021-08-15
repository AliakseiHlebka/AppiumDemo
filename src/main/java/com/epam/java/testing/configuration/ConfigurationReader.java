package com.epam.java.testing.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/test.properties";
    private static final Properties PROPERTIES = new Properties();
    private static ConfigurationReader instance;

    private ConfigurationReader() {

    }

    public static ConfigurationReader get() {
        if (instance == null) {
            instance = new ConfigurationReader();
            try {
                PROPERTIES.load(new FileInputStream(PROPERTIES_FILE_PATH));
            } catch (IOException e) {
                LOGGER.error("Failed to load properties");
            }
        }
        return instance;
    }

    public String environment() {
        return PROPERTIES.getProperty("environment.type");
    }

    public String appPath() {
        return PROPERTIES.getProperty("app.path");
    }

    public String appPackage() {
        return PROPERTIES.getProperty("app.package");
    }

    public String appActivity() {
        return PROPERTIES.getProperty("app.activity");
    }

    public String platformName() {
        return PROPERTIES.getProperty("platform.name");
    }

    public String platformVersion() {
        return PROPERTIES.getProperty("platform.version");
    }

    public String localDeviceName() {
        return PROPERTIES.getProperty("local.device.name");
    }

    public String udid() {
        return PROPERTIES.getProperty("udid");
    }

    public String appiumAddress() {
        return PROPERTIES.getProperty("appium.address");
    }

    public int appiumPort() {
        return Integer.parseInt(PROPERTIES.getProperty("appium.port"));
    }
}
