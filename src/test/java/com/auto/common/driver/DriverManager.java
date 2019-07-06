package com.auto.common.driver;

import org.openqa.selenium.WebDriver;
//https://developers.perfectomobile.com/display/TT/Parallel+Execution+-+Thread+Local%2C+Safeness%2C+and+Synchronization

public abstract class DriverManager {

    //protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
//https://stackoverflow.com/questions/28723077/test-execution-with-multiple-classes-in-testng-using-threads
    private static ThreadLocal<DriverManager> instance = new ThreadLocal<DriverManager>();

    protected WebDriver driver;

    protected abstract void createDriver();

    public WebDriver getDriver(){
        if (null == driver){
            createDriver();
        }
        //return driver.get();
        return driver;
    }

    //mb this way?
    public WebDriver getDriver2(){
        if (null == driver){
            synchronized (DriverManager.class){
                if(null == driver){
                    createDriver();
                }
            }
        }
        return driver;
    }


    public void quitDriver(){
        if(null != driver){
            //driver.get().quit();
            driver.quit();
            driver = null;
        }
    }

}
