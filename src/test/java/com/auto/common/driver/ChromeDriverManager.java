package com.auto.common.driver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager{


    @Override
    protected void createDriver() {
        String exePath = "d:\\Veronica\\Work\\git\\auto\\src\\test\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
}
