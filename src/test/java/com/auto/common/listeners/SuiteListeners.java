package com.auto.common.listeners;

import com.auto.common.BaseTest;
import com.auto.common.utils.Config;
import com.auto.common.utils.FileUtils;
import com.auto.common.utils.TestRun;
import com.auto.common.utils.VelocityUtils;
import com.auto.test.tests.support.IntFailures;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class SuiteListeners extends BaseTest implements ISuiteListener {

    //run as maven clean test -Dgroups=Debug -Dsystem.test.mobileos=iOS -DforkCount=0

    private static String logosPath = "";
    private static String reportPath = "";
    private File logos;
    private File failures;


    @Override
    public void onStart(ISuite iSuite) {

        TestRun.init("");

        //ToDo delete logos  and fail.html if exist

        logosPath = TestRun.isAndroid() ? Config.ConfigProps.ALLURE_ANDROID_LOGO_PATH
                : Config.ConfigProps.ALLURE_WEB_LOGO_PATH;

        System.out.println("SuiteListeners ===logosPath=" + logosPath);
        //clean logos folder
        logos = new File(logosPath);
        if (logos.exists()) {
            FileUtils.deleteRecursive(logos);
        }

        reportPath = TestRun.isAndroid() ? Config.ConfigProps.ALLURE_RESULTS_ANDROID_PATH
                : Config.ConfigProps.ALLURE_RESULTS_WEB_PATH;

        //delete fail.html
        failures = new File(reportPath + "fail.html");
        if (failures.exists()) {
            System.out.println("===deleting fail.ftml file............");
            failures.delete();
        }

    }

    @Override
    public void onFinish(ISuite suite) {

        //ToDo SetUp jenkinsReportDir

        //ToDo: Velocity
        if (suite.getMethodsByGroups().containsKey("Full") && !IntFailures.getFailedBaseProviders().isEmpty()) {
            System.out.println("Generating report...");
            VelocityUtils.generateReport("fail.html", reportPath);
        }


    }
}
