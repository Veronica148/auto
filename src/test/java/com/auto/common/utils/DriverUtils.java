package com.auto.common.utils;

import com.auto.common.exceptions.WindowDidNotOpenException;
import com.auto.common.services.jscommands.commands.GetReadyStatus;
import com.auto.common.utils.Function;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLElement;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by veronica_lapunka on 8/17/18.
 */
public final class DriverUtils {

    private DriverUtils() {

    }

    private static final Logger LOGGER = Logger.getLogger(DriverUtils.class);

    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 120;

    private static final int DEFAULT_TIMEOUT = 10;

    private static final int DEFAULT_PULLING_EVERY = 500;


    public static String switchToNewWindow(WebDriver driver, final Set<String> windowHandles) {

        LOGGER.info("Switching to other window...");



        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        /*ToDo then
        fluentWait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(DEFAULT_PULLING_EVERY, TimeUnit.MILLISECONDS)
                .until(new Function<WebDriver, Boolean>() {


                });*/

//        fluentWait.withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .pollingEvery(DEFAULT_PULLING_EVERY, TimeUnit.MILLISECONDS)
//                .until(new Function<WebDriver, Boolean>() {
//
//                    public Boolean apply(WebDriver driver) {
//                        return driver.getWindowHandles().size() > windowHandles.size();
//                    }
//
//                }, WindowDidNotOpenException.class, "New window didn't open");
        Set<String> newWindowHandles = driver.getWindowHandles();
        String currentWindow = driver.getWindowHandle();
        newWindowHandles.removeAll(windowHandles);
        String windowSwitchTo = newWindowHandles.iterator().next();
        driver.switchTo().window(windowSwitchTo);
        LOGGER.info("... Switched to: " + windowSwitchTo + " from " + currentWindow);
        return windowSwitchTo;
    }

    /**
     * Wait for window title changed
     *
     * @param driver
     *            WebDriver
     * @param currentTitle
     *            Window title at the current moment
     */
    public static void waitForWindowTitleChanged(WebDriver driver, final String currentTitle, long timeoutSeconds) {
        LOGGER.info("Waiting for new title. Current is - " + currentTitle);

        ExpectedCondition<Boolean> ex = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String newTitle = driver.getTitle();
                boolean titleChanged = !newTitle.equals(currentTitle);
                LOGGER.info("New title is - " + newTitle);
                return titleChanged;
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ex);

    }

//    public static void waitForUrlChanged(WebDriver driver, final String previousUrl) {
//        //TimeConstants.IMPLICIT_WAIT = 30
//        waitForUrlChanged(driver, previousUrl, 30);
//
//    }

    public static void waitForUrlChanged(WebDriver driver, final String previousUrl, long secondToWait) {

        ExpectedCondition<Boolean> ex = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                LOGGER.info(!d.getCurrentUrl().equals(previousUrl) + " : " + d.getCurrentUrl() + "    "
                        + previousUrl);
                return !d.getCurrentUrl().equals(previousUrl)
                        && !d.getCurrentUrl().equals("about:blank");

            }
        };

        WebDriverWait wait = new WebDriverWait(driver, secondToWait);
        wait.until(ex);

    }

    /**
     * Wait for element disappearance
     *
     * @param driver
     *            WebDriver
     * @param element
     *            HtmlElement that should disappear
     */
    public static <T extends WebElement> void waitForElementDisappeared(WebDriver driver, final T element) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver, 10, 500))
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver d) {
                            boolean result = false;
                            try {
                                result = !element.isDisplayed();
                            } catch (TimeoutException | ElementNotVisibleException
                                    | NoSuchElementException ex)
                            {
                                result = true;
                            }

                            return result;
                        }
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    }

    /**
     * Wait for element appeared
     *
     * @param driver
     *            WebDriver
     * @param
     *
     */
    public static <T extends WebElement> void waitForElementAppeared(WebDriver driver, final T element) {
        waitForElementAppeared(driver, element, 10);
    }

    public static void waitForElementAppeared(WebDriver driver, final By by) {
        WebElement element = driver.findElement(by);
        waitForElementAppeared(driver, element, 10);
    }

    public static void waitForElementAppeared(WebDriver driver, final By by, long timeoutSec) {
        driver.manage().timeouts().implicitlyWait(timeoutSec, TimeUnit.SECONDS);
        WebElement element = driver.findElement(by);
        waitForElementAppeared(driver, element, timeoutSec);
    }

    /**
     * Wait for element appeared (with custom timeout in secs)
     *
     * @param driver
     *            WebDriver
     * @param
     *
     */
    public static <T extends WebElement> void waitForElementAppeared(WebDriver driver, final T element,
                                                                     long timeoutSec)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver, timeoutSec, 500))
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver d) {

                            boolean result = false;
                            try {
                                result = element.isDisplayed();
                            } catch (org.openqa.selenium.TimeoutException | ElementNotVisibleException
                                    | NoSuchElementException ex)
                            {
                                result = false;
                            }

                            return result;
                        }
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }


    public static <T extends WebElement> void waitForElementDissAppeared(WebDriver driver, final T element) {
        waitForElementDissAppeared(driver, element, 10);
    }
    public static <T extends WebElement> void waitForElementDissAppeared(WebDriver driver, final T element,
                                                                     long timeoutSec) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            (new WebDriverWait(driver, timeoutSec, 500))
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver d) {

                            boolean result = true;
                            try {
                                result = !element.isDisplayed();
                            } catch (org.openqa.selenium.TimeoutException | ElementNotVisibleException
                                    | NoSuchElementException ex)
                            {
                                result = true;
                            }

                            return result;
                        }
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    }
    public static boolean isElementExist(WebDriver driver, By xPath) {
        return driver.findElements(xPath).size() > 0;
    }

    /**
     * Waiting for page load. Default timeout = 120 seconds
     *
     * @param driver
     *            WebDriver
     */
    public static void waitForPageLoad(WebDriver driver) {
        String status;
        int i = 0;
        do {
            status = new GetReadyStatus(driver).execute();
//            Sleeper.SYSTEM_SLEEPER.sleep(1000); ToDo sleep
            i++;
        } while ((!status.equals("complete")) && (i <= DEFAULT_PAGE_LOAD_TIMEOUT));
    }

    /**
     * Goes back to previous Url
     *
     * @param backUrl
     */
    public static void goBack(WebDriver driver, String backUrl) {
        String currentUrl = driver.getCurrentUrl();

        if (!currentUrl.equals(backUrl)) {
            LOGGER.info("Going back to the previous url: " + backUrl);
            DriverUtils.driverGet(driver, backUrl);
            waitForUrlChanged(driver, currentUrl, 5);
        }

    }



    public static void waitForTextChanged(WebDriver driver, String previousText, By byXpath) {
        ExpectedCondition ex = ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(byXpath, previousText));
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ex);
    }

//    public static void waitPopupWindowClosed(WebDriver driver) {
//        FluentWait fluentWait = new FluentWait(driver);
//        LOGGER.info("Waiting for 10 seconds till popup window will be closed...");
//        fluentWait.withTimeout(10L, TimeUnit.SECONDS).pollingEvery(500L, TimeUnit.MILLISECONDS).until(new Function() {
//            public Boolean apply(WebDriver driver) {
//                return driver.getWindowHandles().size() < 2?Boolean.valueOf(true):Boolean.valueOf(false);
//            }
//        });
//        LOGGER.info("... Closed!");
//    }

    /**
     * Closing the Alert if it exists
     *
     * @param driver
     *            WebDriver
     */
    public static void closeAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException | NoSuchWindowException ex) {
            throw new WindowDidNotOpenException("Alert didn't appear!");
        }
    }

    public static void driverGet(WebDriver driver, String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            LOGGER.warn("Timed out waiting for page load. Trying one more time.");
            driver.get(url);
        }
    }

    public static String takeScreenshot(WebDriver driver, String screenshotName){

        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = "d:\\Veronica\\Work\\usefullScreens\\tempFromIdea\\" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        }

        catch (IOException e) {return e.getMessage();}
    }

}
