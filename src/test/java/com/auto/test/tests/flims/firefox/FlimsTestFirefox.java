package com.auto.test.tests.flims.firefox;

import com.auto.common.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlimsTestFirefox extends BaseTest {

    private static final Logger logger = Logger.getLogger(FlimsTestFirefox.class);


    @BeforeClass
    public void setUp() {
//        driver = driverProxy.get().getWebDriverInstance();
        driver.get("https://apps.stein-pilz.com/cd/flims/feature/workshops-refactoring/web-client/login");
        driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys("alex.gay");
        driver.findElement(By.xpath("//button[@id='btn-default']")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void InstallationTest() {
        logger.info("doinng...in Firefox.");
        Assert.assertTrue(true);
    }
}
//mvn clean test -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE" -DsuiteXmlFile=classes-test-testing.xml -Denv=dev -Dgroups=Full -Dbrowser=CHROME -DthreadCount=2 -Dparallel="classes"
