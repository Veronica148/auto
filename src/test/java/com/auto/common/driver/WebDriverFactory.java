package com.auto.common.driver;

import com.auto.common.driver.impl.ChromeWebDriverProxy;
import com.auto.common.driver.impl.FirefoxWebDriverProxy;
import com.auto.common.driver.impl.WebDriverProxy;

public class WebDriverFactory {

    public static WebDriverProxy getDriver(DriverType type){

        WebDriverProxy driver = null;

        switch (type){
            case CHROME:
                driver = new ChromeWebDriverProxy();
                break;
            case FIREFOX:
                driver = new FirefoxWebDriverProxy();
                break;
            case IE:
                //driverProxy = new IEDriverManager();
                break;
        }
        return driver;
    }

}
