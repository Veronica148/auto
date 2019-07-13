package com.auto.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsPage extends Page {

    @FindBy(xpath = "//div[@class='table-info']")
    private WebElement projectAmount;

    public ProjectsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);//WebClientMainPage.class);
        checkPage();
    }

    @Override
    public void checkPage() {
        //ToDo
    }

    public int getAmountOfProjects() {
        int amount = Integer.parseInt(projectAmount.getText());//20 Total
        System.out.println("amount of projects==="+amount);
        return amount;
    }
}
