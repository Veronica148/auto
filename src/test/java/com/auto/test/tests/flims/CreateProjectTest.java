package com.auto.test.tests.flims;

import com.auto.common.BaseTest;
import com.auto.common.driver.DriverManager;
import com.auto.common.driver.DriverManagerFactory;
import com.auto.common.driver.DriverType;
import com.auto.common.pages.*;
import com.auto.common.utils.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(CreateProjectTest.class);

    DriverManager driverManager;
    WebDriver driver;
    private WebClientMainPage webClientMainPage;
    private ProjectsPage projectsPage;
    private LoginPage loginPage;
    private MandantPage mandantPage;
    private SettingsPage settingsPage;


    @BeforeClass(alwaysRun = true)
    public void setUp(){

        driverManager = DriverManagerFactory.getManager(DriverType.valueOf(browserToRun));

        driver = driverManager.getDriver();
        long id = Thread.currentThread().getId();
        System.out.println("Setup " + ". Thread id is: " + id);

        driver.get(url);//("https://apps.stein-pilz.com/cd/flims/feature/workshops-refactoring/web-client/login");

        loginPage = new LoginPage(driver);
        mandantPage = loginPage.login();
        webClientMainPage = mandantPage.chooseMandant();
        settingsPage = webClientMainPage.changeLanguage();
        mandantPage = settingsPage.chooseLanguageAndGoToMandant();
        webClientMainPage = mandantPage.chooseMandant();
        projectsPage = webClientMainPage.goToProjectsPage();

        int amountOfProjects = projectsPage.getAmountOfProjects();
        Assert.assertTrue(amountOfProjects>0);

        //projectsPage.createProject("TestProject","From auto tests","09-09-2019", "10-10-2019");
        projectsPage.createProjectFromCurrentDate("AutoTest","From auto tests");
    }

    @Test(groups = {Config.GroupProps.SMOKE})
    public void CreateProjectTest1(){
        logger.info("CreateProjectTest1...in -------------++++------------------------" + browserToRun);
        Assert.assertTrue(true);
    }

    @Test(groups = {Config.GroupProps.SMOKE})//, dependsOnMethods = {"beforeTest","setUp"})
    public void CreateProjectTest2(){
        logger.info("CreateProjectTest2..in -------------++++------------------------" + browserToRun);
        Assert.assertTrue(true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driverManager.quitDriver();
    }

}
