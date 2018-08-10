package com.auto.common;

import com.auto.common.utils.TestRun;
import org.testng.annotations.BeforeMethod;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public class BaseTest {

    protected String runParams;

    public void setRunParams(String runParams) {
        this.runParams = runParams;
    }

    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        TestRun.init(runParams);
    }

}
