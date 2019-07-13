package com.auto.common.driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract void createDriver();

    public WebDriver getDriver(){
        if (null == driver){
            createDriver();
        }
        return driver;
    }

    public void quitDriver(){
        if(null != driver){
            driver.quit();
            driver = null;
        }
    }

}
