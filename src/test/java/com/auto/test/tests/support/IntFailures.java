package com.auto.test.tests.support;

import com.auto.common.models.intProvider.BaseProvider;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by veronica_lapunka on 8/7/18.
 */

public class IntFailures {

    private static Set<BaseProvider> failedBaseProviders = new HashSet<>();

    public static void addFailureProvider(BaseProvider baseProvider) {
        failedBaseProviders.add(baseProvider);
    }

    public static Set<BaseProvider> getFailedBaseProviders() {
        return failedBaseProviders;
    }

}

