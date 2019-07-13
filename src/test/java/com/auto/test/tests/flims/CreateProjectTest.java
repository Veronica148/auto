package com.auto.test.tests.flims;

import com.auto.common.BaseTest;
import com.auto.common.driver.DriverManager;
import com.auto.common.driver.DriverManagerFactory;
import com.auto.common.driver.DriverType;
import com.auto.common.pages.ProjectsPage;
import com.auto.common.pages.WebClientMainPage;
import com.auto.common.utils.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseTest {
    //https://www.toptal.com/selenium/test-automation-in-selenium-using-page-object-model-and-page-factory

    private static final Logger logger = Logger.getLogger(CreateProjectTest.class);

    DriverManager driverManager;
    WebDriver driver;
    private WebClientMainPage webClientMainPage;
    private ProjectsPage projectsPage;


    @BeforeClass(alwaysRun = true)
    public void setUp(){
        System.out.println("set up-----------------------------");
        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browserToRun));

        driver = driverManager.getDriver();
        long id = Thread.currentThread().getId();
        System.out.println("Setup " + ". Thread id is: " + id);

        //branch to pass in xml, url from constants and stringFormat
        driver.get("https://apps.stein-pilz.com/cd/flims/feature/workshops-refactoring/web-client/login");
        driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys("alex.gay");
        driver.findElement(By.xpath("//button[@id='btn-default']")).click();

        webClientMainPage = new WebClientMainPage(driver);
        projectsPage = webClientMainPage.goToProjectsPage();
        int amountOfProjects = projectsPage.getAmountOfProjects();
        Assert.assertTrue(amountOfProjects>0);
    }

    @Test(groups = {Config.GroupProps.FULL})
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
    }

}
