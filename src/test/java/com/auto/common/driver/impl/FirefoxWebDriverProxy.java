package com.auto.common.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriverProxy extends WebDriverProxy {
    @Override
    protected WebDriver initWebDriverInstance() {
        System.setProperty("webdriver.firefox.marionette","d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\geckodriver.exe");
        return new FirefoxDriver();
    }

    @Override
    public void clearCache() {

    }
}
