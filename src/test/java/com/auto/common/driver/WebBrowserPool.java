package com.auto.common.driver;

import com.auto.common.driver.impl.WebDriverProxy;

import java.util.ArrayList;
import java.util.List;

public class WebBrowserPool {

    private int POOL_SIZE = 2;
    private DriverType DRIVER_TYPE = DriverType.CHROME;
    private List<WebDriverProxy> freeBrowsers = new ArrayList<>();
    private List<WebDriverProxy> takenBrowsers = new ArrayList<>();

    private static WebBrowserPool INSTANCE;

    private WebBrowserPool(){}

    public static WebBrowserPool initBrowserPool(int size, DriverType type){
        if (null == INSTANCE){
            synchronized (WebBrowserPool.class){
                if(null == INSTANCE){
                    INSTANCE = new WebBrowserPool();
                    INSTANCE.POOL_SIZE = size;
                    INSTANCE.DRIVER_TYPE = type;
                    for (int i = 0; i < INSTANCE.POOL_SIZE; i++) {
                        INSTANCE.freeBrowsers.add(WebDriverFactory.getDriver(type));
                    }
                    return INSTANCE;
                }
            }
        }
        throw new RuntimeException("Web Browser's Pool already initialized!");
    }

    public synchronized WebDriverProxy takeBrowserDriver(){
        if(freeBrowsers.isEmpty()){
            throw new RuntimeException("No free browsers at pool!");
        }
        WebDriverProxy driverProxy = freeBrowsers.get(0);
        freeBrowsers.remove(driverProxy);
        takenBrowsers.add(driverProxy);
        return driverProxy;
    }

    public synchronized void returnBrowserDriver(WebDriverProxy driverProxy){
        if(!takenBrowsers.contains(driverProxy)){
            throw new RuntimeException("Unknown browser's driver instance!");
        }
        driverProxy.clearCache();
        takenBrowsers.remove(driverProxy);
        freeBrowsers.add(driverProxy);
    }

    public void shutdown(){
        freeBrowsers.forEach(WebDriverProxy::close);
        takenBrowsers.forEach(WebDriverProxy::close);
        freeBrowsers.clear();
        takenBrowsers.clear();
    }

    public int getPoolSize() {
        return POOL_SIZE;
    }

    public DriverType getDriverType() {
        return DRIVER_TYPE;
    }

    public static WebBrowserPool getPool() {
        return INSTANCE;
    }
}
