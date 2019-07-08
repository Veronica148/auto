package com.auto.common.driver.impl;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverProxy {

    protected WebDriver driver;

    protected abstract WebDriver initWebDriverInstance();

    public abstract void clearCache();

    protected WebDriverProxy() {
        driver = initWebDriverInstance();
        driver.manage().window().maximize();
    }

    public WebDriver getWebDriverInstance() {
        return driver;
    }

    public void close() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
}
