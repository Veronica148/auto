package com.auto.common.pages.blocks;

import com.auto.common.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Name("Vehicle search window")
@Block(@FindBy(xpath = "//ux-dialog"))
public class VehicleSearch extends HtmlElement {

    private static final Logger logger = Logger.getLogger(VehicleSearch.class);

    @FindBy(xpath = "//div[@class='dialog-header-content']/h3]")
    private HtmlElement title;

    @FindBy(xpath = "//button[@click.trigger='controller.cancel()']")
    private Button close;

    @FindBy(id = "pnTxt")
    private TextInput plateNumber;

    @FindBy(xpath = "//button[@click.trigger='search()']")
    private Button search;

    @FindBy(xpath = "//button[@click.trigger='save()']")
    private Button save;

    @FindBy(xpath = "//button[@click.trigger='cancel()']")
    private Button cancel;

    @FindBy(xpath = "//button[@click.trigger='add()']")
    private Button add;

    @FindBy(xpath = "//td/input[@type='checkbox']")
    private CheckBox car;

    @FindBy(xpath = "//div[contains(@class,'from-date')]//span[contains(@class,'daimler-icon')]")
    private HtmlElement fromDatePicker;

    @FindBy(xpath = "//div[contains(@class,'to-date')]//span[contains(@class,'daimler-icon')]")
    private HtmlElement toDatePicker;

    @FindBy(xpath = "//div[@class='busy-indicator active']")
    private WebElement buffering;

    private String dateTemplateXPath = "//td[@class='day'][text()=%s]";
    private String newDateTemplateXPath = "//td[@class='new day'][text()=%s]";

    private static final DateFormat day = new SimpleDateFormat("d");

    public void searchByPlateNumber(WebDriver driver, String plNumber){
        plateNumber.sendKeys(plNumber);
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        search.click();
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        car.select();
        add.click();
        setFromDateFromPicker(driver);
        setToDateFromPicker(driver);
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        save.click();
    }

    private void setFromDateFromPicker(WebDriver driver){
        fromDatePicker.click();
        logger.info("Current day:" + getCurrentDay());
        driver.findElement(By.xpath(String.format(dateTemplateXPath, Integer.parseInt(getCurrentDay())))).click();

    }

    private void setToDateFromPicker(WebDriver driver){

        toDatePicker.click();
        String curDate = getCurrentDay();
        logger.info("Current day:" + getCurrentDay());
        //toDo loging when end of month (with 'new day')
        int date = Integer.parseInt(curDate);
        int newDate = date + 3;
        if(newDate > 28) {
            driver.findElement(By.xpath(String.format(dateTemplateXPath, newDate))).click();
        } else{
            driver.findElement(By.xpath(String.format(newDateTemplateXPath, 3))).click();
        }

        logger.info("Current day:" + newDate);

    }

    //toDo then to calendar Utils
    private String getCurrentDay(){
        Date date = new Date();
        return day.format(date);
    }

}
