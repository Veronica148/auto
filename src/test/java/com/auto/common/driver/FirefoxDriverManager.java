package com.auto.common.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.firefox.marionette","d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
