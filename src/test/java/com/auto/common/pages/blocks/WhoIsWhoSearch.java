package com.auto.common.pages.blocks;

import com.auto.common.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("WhoIsWho search window")
@Block(@FindBy(xpath = "//ux-dialog"))
public class WhoIsWhoSearch extends HtmlElement{

    private static final Logger logger = Logger.getLogger(WhoIsWhoSearch.class);

    @FindBy(xpath = "//div[@class='dialog-header-content']/h3]")
    private HtmlElement title;

    @FindBy(xpath = "//button[@click.trigger='controller.cancel()']")
    private Button close;

    @FindBy(id = "wiwTxt")
    private TextInput whoIsWhoInput;

    @FindBy(xpath = "//input[@value.bind='searchLastname']")
    private TextInput lastName;

    @FindBy(xpath = "//input[@value.bind='searchFirstname']")
    private TextInput firstName;

    @FindBy(xpath = "//button[@click.trigger='search()']")
    private Button search;

    @FindBy(xpath = "//button[@click.trigger='cancel()']")
    private Button cancel;

    @FindBy(xpath = "//tr[@click.trigger='add(person)']")
    private HtmlElement person;

    public void searchByWhoIsWho(WebDriver driver, String whoIsWho){
        whoIsWhoInput.sendKeys(whoIsWho);
        search.click();
        DriverUtils.waitForElementAppeared(driver, person);
        person.click();
    }

    //ToDo search via pressing on Search btn
    //ToDo search via last name
    //ToDo search via first name
    //ToDo search via several params
}
