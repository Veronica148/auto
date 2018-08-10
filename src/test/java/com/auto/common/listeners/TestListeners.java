package com.auto.common.listeners;


import com.auto.common.BaseTest;
import com.auto.common.utils.Config;
import com.auto.common.utils.TestIDs;
import com.auto.common.utils.TestRun;
import com.auto.test.tests.support.IntFailures;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.internal.ResultMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class TestListeners extends BaseTest implements IRetryAnalyzer, ITestListener, IInvokedMethodListener {

    private static final Logger logger = Logger.getLogger(TestListeners.class);

    private static ThreadLocal<String> testID = new ThreadLocal<String>();
    private Map<String, AtomicInteger> retries = new HashMap<String, AtomicInteger>();
    private IResultMap failedCases = new ResultMap();

    private static String RUN_PROPS = "runProps";

    private static final int MAX_COUNT = Config.ConfigProps.RERUN_ON_FAILURE_COUNT;

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("========TEST STARTED========");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("========TEST FINISHED========");
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult result) {
        IRetryAnalyzer retryAnalyzer = result.getMethod().getRetryAnalyzer();
        if (retryAnalyzer != null) {
            if (result.getStatus() == ITestResult.SKIP && result.getThrowable() != null) {
                result.setStatus(ITestResult.FAILURE);
                Reporter.setCurrentTestResult(result);
            }
        }
    }

    public boolean retry(ITestResult result) {
        return false;
    }

    @Override
    public void onTestStart(ITestResult result) {

        try {
            //set testId
            String id = getId(result);
            testID.set(id.toLowerCase());
            logger.info(testID.get());
        } catch (Exception e){
            logger.trace("Failed to log test ids");
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        teardownTest(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        teardownTest(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        teardownTest(result);
    }

    private void teardownTest(ITestResult result) {

        String status = result.isSuccess() ? "SUCCESS" : "FAILURE";
        logger.info("======" + status + "======");
        logger.info("Test: " + result.getInstanceName() + "." + result.getName());

        try {
            //ToDo : MobileDriverManager.stopDriver();
        } catch (Exception e) {
            logger.info("Failed to stop driver.");
        }

        try {
            if (result.isSuccess()) {
                // add the test passed id
                TestIDs.addPassedTest(testID.get());
            } else {
                // check if the test should be re-executed based on retry logic
                if (result.getMethod().getRetryAnalyzer() != null && Config.ConfigProps.RERUN_ON_FAILURE) {
                    TestListeners testRetryAnalyzer = (TestListeners) result.getMethod().getRetryAnalyzer();
                    if (testRetryAnalyzer.getCount(result).intValue() > 0) {
                        result.setStatus(ITestResult.FAILURE);
                    } else {
                        failedCases.addResult(result, result.getMethod());
                        IntFailures.addFailureProvider(TestRun.getBaseProvider());
                    }
                } else {
                    //if rerun=false for displaying in velocity report
                    IntFailures.addFailureProvider(TestRun.getBaseProvider());
                }

                // add the test failed id
                TestIDs.addFailedTest(testID.get());
            }
        } catch (Exception e) {
           logger.info("Failed to log test id to results.");
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    private String getId(ITestResult result) {
        logger.info("Run props: " + ArrayUtils.toString(result.getAttribute(RUN_PROPS)));

        if (result.getMethod().getMethodName().equalsIgnoreCase(result.getName())) {
            return "TestID: " + result.getMethod().getMethodName() + " " + "(" + TestRun.getMobileOS().value() + " " + TestRun.getDeviceCategory().value() + ")";
        } else {
            return "TestID: " + result.getMethod().getMethodName() + " " + result.getName() + " " + "(" + TestRun.getMobileOS().value() + " " + TestRun.getDeviceCategory().value() + ")";
        }
    }

    private AtomicInteger getCount(ITestResult result) {
        String id = getId(result);
        retries.computeIfAbsent(id, k -> new AtomicInteger(MAX_COUNT));
        return retries.get(id);
    }

}
