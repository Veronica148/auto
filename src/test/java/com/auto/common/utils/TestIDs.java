package com.auto.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veronica_lapunka on 8/7/18.
 */
public class TestIDs {

    private static ThreadLocal<String> testId = new ThreadLocal<String>();
    private static List<String> passedTestIds = new ArrayList<String>();
    private static List<String> failedTestIds = new ArrayList<String>();

    public static void setTestId(String id) {
        testId.set(id);
    }

    public static String getTestId() {
        return testId.get();
    }

    public static void addPassedTest(String testId) {
        passedTestIds.add(testId);
    }

    public static void addFailedTest(String testId) {
        failedTestIds.add(testId);
    }

    public static List<String> getPassedTestIds() {
        return passedTestIds;
    }

    public static List<String> getFailedTestIds() {
        return failedTestIds;
    }
}
