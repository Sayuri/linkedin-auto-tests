package tests;


import org.testng.annotations.*;
import utils.ConfigurationManager;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class BaseTest {

    /**
     * Method that is run before each class
     */
    @BeforeClass
    public void beforeClass() {
        ConfigurationManager.getDriver().get("https://www.linkedin.com");
        ConfigurationManager.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    /**
     * Method that is run after each class
     */
    @AfterClass
    public void afterClass() {
        System.out.println("starting basetest's afterclass");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ConfigurationManager.getDriver() != null) {
            ConfigurationManager.getDriver().quit();
        }
    }
}
