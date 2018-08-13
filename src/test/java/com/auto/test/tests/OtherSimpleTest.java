package com.auto.test.tests;

import com.auto.common.BaseTest;
import com.auto.common.utils.Config;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class OtherSimpleTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(OtherSimpleTest.class);

    @Test(groups = {Config.GroupProps.FULL})
    public void testGeneral(){
        Assert.assertTrue(true, "Not true!!!!");
    }

    @Test(groups = {Config.GroupProps.FULL})
    public void testGeneral2(){
        //read smth from .properties file and print

        System.out.println("Host from properties: " + BaseTest.apiHost);

        Assert.assertTrue(5 == 6, "Not true!!!!");
    }
}
