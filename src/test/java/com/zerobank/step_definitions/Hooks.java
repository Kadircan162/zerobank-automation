package com.zerobank.step_definitions;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {
    WebDriver driver = Driver.get();

    @Before("@setupAndTeardown")
    public void setUp(){
        driver.get(ConfigurationReader.get("url"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After("@setupAndTeardown")
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        Driver.closeDriver();
    }
}
