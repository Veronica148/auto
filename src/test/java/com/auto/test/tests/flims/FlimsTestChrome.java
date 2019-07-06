package com.auto.test.tests.flims;

import com.auto.common.BaseTest;
import com.auto.common.driver.DriverManager;
import com.auto.common.driver.DriverManagerFactory;
import com.auto.common.driver.DriverType;
import com.auto.common.utils.Config;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FlimsTestChrome extends BaseTest {

    private static final Logger logger = Logger.getLogger(FlimsTestChrome.class);
    //private static WebDriver driver = null;
    DriverManager driverManager;
    WebDriver driver;

//    @BeforeClass(alwaysRun = true)
//    public void beforeTest(){
//        System.out.println("beforeTest-----------------------------");
//        //driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
//        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browserToRun));
//        //driver = driverManager.getDriver();
//        driver = driverManager.getDriver2();
//    }

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        System.out.println("set up-----------------------------");
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browserToRun));
        //driver = driverManager.getDriver();
        driver = driverManager.getDriver2();
        long id = Thread.currentThread().getId();
        System.out.println("Setup " + ". Thread id is: " + id);

        //setting driver property
//        String exePath = "d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", exePath);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("https://apps.stein-pilz.com/cd/flims/feature/workshops-refactoring/web-client/login");
        driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys("alex.gay");
        takeScreenshot(driver, "before_logged1");
        driver.findElement(By.xpath("//button[@id='btn-default']")).click();
        takeScreenshot(driver, "logged1");
    }

    @Test(groups = {Config.GroupProps.FULL})//, dependsOnMethods = {"beforeTest","setUp"})
    public void InstallationTest(){
        logger.info("doinng...in -------------++++------------------------" + browserToRun);
        Assert.assertTrue(true);
    }

    @Test(groups = {Config.GroupProps.FULL})//, dependsOnMethods = {"beforeTest","setUp"})
    public void InstallationTest2(){
        logger.info("doinng.2..in -------------++++------------------------" + browserToRun);
        Assert.assertTrue(true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driverManager.quitDriver();
//        if(driver != null)
//            driver.quit();
    }

    private static String takeScreenshot(WebDriver driver, String screenshotName){

        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = "d:\\Veronica\\Work\\usefullScreens\\tempFromIdea\\" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        }

        catch (IOException e) {return e.getMessage();}
    }

}
