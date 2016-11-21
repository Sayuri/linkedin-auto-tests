package tests.profileInformationEditing;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EditPhotoPage;
import pages.HomePage;
import pages.RegistrationAndLoginPage;
import tests.BaseTest;

import static java.lang.Thread.sleep;

public class UploadPhotoTest extends BaseTest {
    private HomePage homePage;
    private EditPhotoPage editPhotoPage;

    @BeforeClass
    public void beforeTest() {
        RegistrationAndLoginPage registrationAndLoginPage = new RegistrationAndLoginPage();
        homePage = registrationAndLoginPage.loginAndGoToHomePage("dem.iuliia.p@gmail.com", "dem.iuliia.p.password");
    }

    @Test
    public void uploadPhotoTest() {
        editPhotoPage = homePage.goToEditPhotoPage();
        editPhotoPage.uploadAPhoto();
        Assert.assertTrue(editPhotoPage.getProfileImage().isDisplayed());
    }

    @AfterClass
    public void afterTest() {
        editPhotoPage.clickOnChangePhotoButton();
        editPhotoPage.getDeleteButton().click();
        editPhotoPage.waitUntilElementIsDisplayed(editPhotoPage.getProfileImagePlaceHolder());
        Assert.assertEquals(editPhotoPage.getPhotoHolderText(), "Add a photo");
    }
}
