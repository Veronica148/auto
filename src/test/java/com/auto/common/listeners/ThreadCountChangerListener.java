package com.auto.common.listeners;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class ThreadCountChangerListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        System.out.println("in ThCountChnagerListener...............");
        int count = Integer.parseInt(System.getProperty("threadCount","-1"));
        System.out.println("-----------------count===============" + count);
        if(count<=0)
            return;

        for (XmlSuite suite: suites){
            suite.setThreadCount(count);
        }

    }
}
