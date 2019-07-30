package com.auto.common.pages;

import com.auto.common.pages.blocks.VehicleSearch;
import com.auto.common.pages.blocks.WhoIsWhoSearch;
import com.auto.common.utils.DriverUtils;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectsPage extends Page {

    private static final Logger logger = Logger.getLogger(ProjectsPage.class);

    private WhoIsWhoSearch whoIsWhoSearch;
    private VehicleSearch vehicleSearch;

    @FindBy(xpath = "//div[@class='table-info']")
    private WebElement projectAmount;

    @FindBy(xpath = "//input[contains(@class,'search')]")
    private WebElement searchInput;

    @FindBy(xpath = "//button/span[contains(@class,'add')]")
    private WebElement createNewBtn;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> projects;

    //ProjectDetails
    @FindBy(id = "nameTxt")
    private WebElement prName;

    @FindBy(id = "descriptionTxt")
    private WebElement prDescription;

    @FindBy(xpath = "//div[contains(@class,'start-date')]//input")
    private WebElement startDateInput;

    @FindBy(xpath = "//div[contains(@class,'start-date')]//span[contains(@class,'daimler-icon')]")
    private HtmlElement startDatePicker;

    @FindBy(xpath = "//div[contains(@class,'end-date')]//input")
    private WebElement endDateInput;

    @FindBy(xpath = "//div[contains(@class,'end-date')]//span[contains(@class,'daimler-icon')]")
    private HtmlElement endDatePicker;

//    @FindBy(xpath = "//td[@class='active day']")
//    private HtmlElement currentDate;

    private String dateTemplateXPath = "//td[@class='day'][text()=%s]";
    private String newDateTemplateXPath = "//td[@class='new day'][text()=%s]";

    @FindBy(id = "plantTxt")
    private WebElement plant;

    @FindBy(xpath = "//button[@click.trigger='save()']")
    private WebElement saveBtn;

    @FindBy(xpath = "//button[@click.trigger = 'openSearchResponsible()']")
    private Button addResponsibleBtn;

    @FindBy(xpath = "//button[@click.trigger = 'openSearchVehicle()']")
    private Button addVehicleBtn;

    @FindBy(xpath = "//div[@class='busy-indicator active']")
    private WebElement buffering;

    private static final DateFormat dateAll = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
    private static final DateFormat day = new SimpleDateFormat("d");

    public ProjectsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
        //or this we can use HtmlElementLoader.populatePageObject(this,driver);
        checkPage();
    }

    @Override
    public void checkPage() {
        DriverUtils.waitForElementAppeared(driver, projects.get(0));
    }

    public int getAmountOfProjects() {
        //projects amount in format: number Total
        int amount = Integer.parseInt(projectAmount.getText().split("\\s")[0]);//20 Total
        logger.info("amount of projects===" + amount);
        return amount;
    }

    public void createProject(String name, String description, String startD, String endD){
        createProjCommon(name, description);

        startDateInput.sendKeys(startD);
        startDateInput.sendKeys(Keys.ENTER);
        endDateInput.sendKeys(endD);
        endDateInput.sendKeys(Keys.ENTER);
        plant.sendKeys("123");
        DriverUtils.waitForElementDissAppeared(driver, buffering);

//        DriverUtils.takeScreenshot(driver, "afterSleep");
        fillResponsible();
        DriverUtils.waitForElementDissAppeared(driver, buffering);

        fillVehicle();
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        saveBtn.click();
    }

    private void createProjCommon(String name, String description){
        createNewBtn.click();
        String newPrName = getRandomStr()+ name;
        typeInField(prName,newPrName);
        prDescription.sendKeys(description);
    }


    public void createProjectFromCurrentDate(String name, String description){
        createProjCommon(name, description);

        setStartDateFromPicker();
        setEndDateFromPicker();

        plant.sendKeys("789");
        DriverUtils.waitForElementDissAppeared(driver, buffering);

        fillResponsible();
        DriverUtils.waitForElementDissAppeared(driver, buffering);

        fillVehicle();
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        saveBtn.click();
    }
    private String getRandomStr(){
        Date date = new Date();
        return dateAll.format(date);
    }

    private String getCurrentDay(){
        Date date = new Date();
        return day.format(date);
    }

    public void typeInField(WebElement el, String value){
        String val = value;
        el.clear();

        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            el.sendKeys(s);
        }
    }

    private void fillResponsible(){
        addResponsibleBtn.click();
        whoIsWhoSearch.searchByWhoIsWho(driver, "alex.gay");
    }

    private void fillVehicle(){
        addVehicleBtn.click();
        //buffering
        vehicleSearch.searchByPlateNumber(driver,"S-WV 126");
    }

    private void setStartDateFromPicker(){
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        startDatePicker.click();
        logger.info("Current day:" + getCurrentDay());
        driver.findElement(By.xpath(String.format(dateTemplateXPath, Integer.parseInt(getCurrentDay())))).click();
    }

    private void setEndDateFromPicker(){
        DriverUtils.waitForElementDissAppeared(driver, buffering);
        endDatePicker.click();
        String curDate = getCurrentDay();
        logger.info("Current day:" + getCurrentDay());
        //toDo loging when end of month (with 'new day')
        int date = Integer.parseInt(curDate);
        int newDate = date + 3;
        if(newDate < 28) {
            driver.findElement(By.xpath(String.format(dateTemplateXPath, newDate))).click();
        } else{
            newDate = 3;
            driver.findElement(By.xpath(String.format(newDateTemplateXPath, newDate))).click();
        }
        logger.info("Current day:" + newDate);
    }
}
