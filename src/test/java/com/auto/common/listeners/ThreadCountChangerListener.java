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
        suites.forEach(s -> System.out.println(s.getThreadCount()));
        if(count<=0)
            //ToDo add some parameter to TestNG xml, where we store the value of defaultThreadAmount. and if we don't pass through cmd, we use it.
            //count = valueFromXML
            return;

        for (XmlSuite suite: suites){
            suite.setThreadCount(count);
        }

    }
}
