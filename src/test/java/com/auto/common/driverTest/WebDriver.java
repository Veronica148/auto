package com.auto.common.driverTest;

import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver {

    private static WebDriver instance;
    private FirefoxDriver firefoxDriverInstance;

    private WebDriver(){
        firefoxDriverInstance = new FirefoxDriver();
    }

    public static WebDriver getInstance(){
        if(instance == null){
            synchronized (WebDriver.class){
                if(instance == null){
                    instance = new WebDriver();
                }
            }
        }
        return instance;
    }

    public FirefoxDriver getFirefoxDriver(){
        return firefoxDriverInstance;
    }

}
