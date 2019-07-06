package com.auto.common.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.firefox.marionette","d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
