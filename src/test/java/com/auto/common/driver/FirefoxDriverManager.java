package com.auto.common.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        ClassLoader classLoader = getClass().getClassLoader();
        String exePath = classLoader.getResource("drivers/geckodriver.exe").getPath();
        System.setProperty("webdriver.firefox.marionette", exePath);

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
