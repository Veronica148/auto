package com.auto.test.tests;

import com.auto.common.BaseTest;
import com.auto.common.listeners.TestListeners;
import com.auto.common.models.intProvider.BaseProvider;
import com.auto.common.utils.Config;
import com.auto.common.utils.TestRun;
import com.auto.test.tests.pageobjects.IntProvidersPicker;
import com.auto.test.tests.support.DataProviderManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by veronica_lapunka on 7/31/18.
 */
public class SimpleTest extends BaseTest implements ITest{

    private static final Logger logger = Logger.getLogger(SimpleTest.class);

    private String brand;
    private String providerName;
    private String providerMVPD;
    private String locale;
    private String environment;

    private IntProvidersPicker intProvidersPicker;


    @Factory(dataProvider = "intTveData", dataProviderClass = DataProviderManager.class)
    public SimpleTest(String runParams, String providerName, String providerMVPD, String locale, String brand, String env){
        super.setRunParams(runParams);
        this.brand = brand;
        this.providerName = providerName;
        this.providerMVPD = providerMVPD;
        this.locale = locale;
        this.environment = env;
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        intProvidersPicker = new IntProvidersPicker();
    }

    @Test(groups = {Config.GroupProps.DEBUG})
    public void testGeneralWork(){
        BaseProvider bProvider = new BaseProvider(locale, brand, providerName, environment, runParams);
        TestRun.setBaseProvider(bProvider);
        logger.info(bProvider);

        Random random = new Random();
        boolean testT = random.nextBoolean();
        System.out.println("---------testT------" + testT);
        Assert.assertTrue(testT, "Not true!!!!");
        String someStr = intProvidersPicker.logInWithProviderForLocale(providerMVPD, locale, brand, environment, true);
        System.out.println(someStr + "=================");
    }

//    @Test
//    public void testGeneralWork2(){
//        logger.info("test2...");
//        Assert.assertTrue(6 == 6, "Not true!!!!");
//    }

    @Override
    public String getTestName() {
        return providerMVPD + "_" + brand + "_" + locale;
    }
}
