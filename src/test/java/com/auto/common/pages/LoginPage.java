package com.auto.common.pages;

import com.auto.common.constants.EnvironmentConstants;
import com.auto.common.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

    private final Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@type='submit'][@name='submit_login']")
    private WebElement loginBtn;

    public LoginPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        checkPage();
    }


    @Override
    public void checkPage() {
        DriverUtils.waitForElementAppeared(driver, loginInput);
    }

    public MandantPage login() {
        logger.info("Login.....");
        loginInput.sendKeys(EnvironmentConstants.LOGIN_USERNAME);
        loginBtn.click();
        return new MandantPage(driver);
    }


}
