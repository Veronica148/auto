package com.auto.common;

import com.auto.common.constants.EnvironmentConstants;
import com.auto.common.utils.Config;
import com.auto.common.utils.TestRun;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

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
    protected static String url = "";

    protected static String browserToRun = "";

    public void setRunParams(String runParams) {
        this.runParams = runParams;
    }

    @BeforeSuite(alwaysRun = true)
    public void setupEnv() { System.out.println("Setting environment....");

        String environment = System.getProperty(sysProp);

        if (environment == null) {
            environment = "qa";
        }
        String branch = Config.ConfigProps.BRANCH;
        url = String.format(EnvironmentConstants.WEB_CLIENT_URL, branch);

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
            //driver = prop.getProperty("browser");
            //or we can take default value from testng.xml
            browserToRun = Config.ConfigProps.BROWSER.toUpperCase();
        }
        System.out.println("========driver===" + browserToRun);
    }


    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        TestRun.init(runParams);
    }



}
