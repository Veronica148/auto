package com.auto.common.pages;

import com.auto.common.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends Page {

    @FindBy(xpath = "//select[@id='ddlLang']")
    private WebElement langDropdown;

    @FindBy(xpath = "//select[@id='ddlLang']/option[text()='Englisch']")
    private WebElement englLang;

    @FindBy(xpath = "//button[@click.trigger='changeLanguage()']")
    private WebElement okBtn;

    @FindBy(xpath = "//a[@href.one-time='getMandantsUrl()']")
    private WebElement mandantLink;

    public SettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        checkPage();
    }

    @Override
    public void checkPage() {
        DriverUtils.waitForElementAppeared(driver, mandantLink);
    }

    public MandantPage chooseLanguageAndGoToMandant() {
        langDropdown.click();
        englLang.click();
        okBtn.click();
        mandantLink.click();
        return new MandantPage(driver);
    }
}
