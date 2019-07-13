package com.auto.common.pages;

import com.auto.common.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebClientMainPage extends Page {

    @FindBy(xpath = "//div[@class='app-tile-header-name'][text()='Projects']")
    private WebElement projects;

    @FindBy(xpath = "//label[contains(@class,'app-tile')][contains(text(),'Projects')]")
    private WebElement projectHeader;


    public WebClientMainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);//WebClientMainPage.class);
        //checkPage();
    }

    @Override
    public void checkPage() {
        DriverUtils.waitForElementAppeared(driver, projectHeader);
    }

    public ProjectsPage goToProjectsPage(){
        projects.click();

        return new ProjectsPage(driver);
    }

    //Projects

    //Installations

    //WS
    //WS tasks
    //Dashboard

    // EmplDash
    //WS auth
    //...
    //Rent
    //Vehicle search
}
