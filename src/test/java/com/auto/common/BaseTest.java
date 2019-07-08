package com.auto.common;

import com.auto.common.driver.DriverType;
import com.auto.common.driver.WebBrowserPool;
import com.auto.common.driver.WebDriverFactory;
import com.auto.common.driver.impl.WebDriverProxy;
import com.auto.common.utils.Config;
import com.auto.common.utils.TestRun;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public class BaseTest {

    protected String runParams;

    protected static String apiHost = "";
    protected static final String sysProp = "env";
    protected static final String propFileName = "application.properties";
    protected static final String browser = "browser";

    protected static String browserToRun = "";

    protected static WebBrowserPool pool;
    protected WebDriver driver;
    protected WebDriverProxy driverProxy;


    public void setRunParams(String runParams) {
        this.runParams = runParams;
    }

    @BeforeSuite(alwaysRun = true)
    public void setupEnv() { System.out.println("Setting environment....");

        String environment = System.getProperty(sysProp);

        if (environment == null) {
            environment = "qa";
        }

        InputStream inputStream;
        Properties prop = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (IOException ex) {

        }
        System.out.println("environment BaseTest: "+ environment);
        apiHost = prop.getProperty(environment);

        //System.out.println("Browser..." + TestRun.getBrowser()); - not null if we run testng.xml
        browserToRun = System.getProperty(browser);
        if (browserToRun == null) {
            System.out.println("--------------------in null if--------------");
            //taking value from property file, if we didn't pass -Dbrowser in cmd
            //driverProxy = prop.getProperty("browser");
            //or we can take default value from testng.xml
            browserToRun = Config.ConfigProps.BROWSER.toUpperCase();
        }
        System.out.println("========driverProxy===" + browserToRun);
//        driverProxy.set(WebDriverFactory.getDriver(DriverType.valueOf(browserToRun)));
        //ToDo add some parameter to TestNG xml, where we store the value of defaultThreadAmount. and if we don't pass through cmd, we use it.

        int threadCount = Integer.parseInt(System.getProperty("threadCount","2"));//"-1"));

        pool = WebBrowserPool.initBrowserPool(threadCount, DriverType.valueOf(browserToRun));
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        driverProxy = pool.takeBrowserDriver();
        driver = driverProxy.getWebDriverInstance();
    }


    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        TestRun.init(runParams);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        pool.returnBrowserDriver(driverProxy);
        driver = null;
        driverProxy = null;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
//        driverProxy.get().close();
        pool.shutdown();
    }

}
