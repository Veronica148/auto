package com.auto.test.tests.flims;

import com.auto.common.BaseTest;
import com.auto.common.driver.DriverManager;
import com.auto.common.driver.DriverManagerFactory;
import com.auto.common.driver.DriverType;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlimsTestFirefox extends BaseTest {

    private static final Logger logger = Logger.getLogger(FlimsTestFirefox.class);
    //private static WebDriver driver = null;
    DriverManager driverManager;
    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
        driver = driverManager.getDriver();
    }

    @BeforeClass
    public void setUp(){
        //setting driver property - firefox 46 version -https://ftp.mozilla.org/pub/firefox/releases/46.0.1/win64/en-US/
//        System.setProperty("webdriver.firefox.marionette","d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\geckodriver.exe");
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
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
    public void InstallationTest(){
        logger.info("doinng...in Firefox.");
        Assert.assertTrue(true);
    }

    @AfterClass
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }
}
