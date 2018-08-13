package com.auto.common;

import com.auto.common.utils.TestRun;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

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

    public void setRunParams(String runParams) {
        this.runParams = runParams;
    }

    @BeforeSuite(alwaysRun = true)
    public void setupEnv() {
        System.out.println("Setting environment....");

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
    }


    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        TestRun.init(runParams);
    }

}
