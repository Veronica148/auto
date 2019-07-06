package com.auto.common.driver;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type){

        DriverManager driverManager = null;

        switch (type){
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            case IE:
                //driverManager = new IEDriverManager();
                break;
        }
        return driverManager;
    }

}
