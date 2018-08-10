package com.auto.test.tests;

import com.auto.common.BaseTest;
import com.auto.common.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class OtherSimpleTest extends BaseTest {

    @Test(groups = {Config.GroupProps.FULL})
    public void testGeneral(){
        Assert.assertTrue(true, "Not true!!!!");
    }

    @Test(groups = {Config.GroupProps.FULL})
    public void testGeneral2(){
        Assert.assertTrue(5 == 6, "Not true!!!!");
    }
}
