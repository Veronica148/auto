package com.auto.common.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager{

    @Override
    protected void createDriver() {

        ClassLoader classLoader = getClass().getClassLoader();
        String exePath = classLoader.getResource("drivers/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", exePath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
