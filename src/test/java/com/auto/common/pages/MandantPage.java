package com.auto.common.pages;

import com.auto.common.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MandantPage extends Page {

    private final Logger logger = Logger.getLogger(MandantPage.class);

    @FindBy(xpath = "//div[@class='apps']")
    private WebElement mandantCells;

    @FindBy(xpath = "//div[@class='apps']/div[2]")
    private WebElement secondMandant;

    public MandantPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        checkPage();
    }

    @Override
    public void checkPage() {
        DriverUtils.waitForElementAppeared(driver, mandantCells);
    }

    public WebClientMainPage chooseMandant(){
        logger.info("Choosing mandant...");
        secondMandant.click();
        logger.info("Set mandant...");
        return new WebClientMainPage(driver);
    }
}
