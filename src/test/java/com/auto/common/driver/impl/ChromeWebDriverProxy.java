package com.auto.common.driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriverProxy extends WebDriverProxy {
    @Override
    protected WebDriver initWebDriverInstance() {
        String exePath = "d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driverProxy", exePath);
        return new ChromeDriver();
    }

    @Override
    public void clearCache() {

    }
}
