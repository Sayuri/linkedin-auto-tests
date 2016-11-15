package tests.registrationAndLoginTestSuite;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationAndLoginPage;
import tests.BaseTest;

public class LoginTest extends BaseTest{

    /**
     * Positive login test with valid credentials
     * The valid credentials are put to the required fields and the signIn button is pressed
     * New HomePage object is received and the identityBlock presents checked
     */
    @Test
    public void loginWithValidEmail() {
        RegistrationAndLoginPage registrationAndLoginPage = new RegistrationAndLoginPage();
        HomePage homePage = registrationAndLoginPage.loginAndGoToHomePage("dem.iuliia.p@gmail.com", "dem.iuliia.p.password");
        Assert.assertTrue(homePage.getIdentityBlock().isDisplayed(), "Identity block is not displayed");
    }
}
