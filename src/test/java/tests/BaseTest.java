package tests;


import org.testng.annotations.*;
import utils.ConfigurationManager;

import java.util.concurrent.TimeUnit;

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
        if(ConfigurationManager.getDriver() != null) {
            ConfigurationManager.getDriver().quit();
        }
    }
}
