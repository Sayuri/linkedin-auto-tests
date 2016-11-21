package tests.profileInformationEditing;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EditPhotoPage;
import pages.HomePage;
import pages.RegistrationAndLoginPage;
import tests.BaseTest;

public class UploadPhotoTest extends BaseTest {
    private HomePage homePage;
    private EditPhotoPage editPhotoPage;

    /**
     * Method that is run before the class
     * New RegistrationAndLoginPage object is created
     * Logs in with valid credentials
     * New HomePage object is created
     */
    @BeforeClass
    public void beforeClass() {
        RegistrationAndLoginPage registrationAndLoginPage = new RegistrationAndLoginPage();
        homePage = registrationAndLoginPage.loginAndGoToHomePage("dem.iuliia.p@gmail.com", "dem.iuliia.p.password");
    }

    /**
     * Navigates to EditPhotoPage
     * Photo is loaded
     * Asserts that photo is loaded
     */
    @Test
    public void uploadPhotoTest() {
        editPhotoPage = homePage.goToEditPhotoPage();
        editPhotoPage.uploadAPhoto();
        Assert.assertTrue(editPhotoPage.getProfileImage().isDisplayed());
    }

    /**
     * Method that is run after class
     * Profile photo is removed
     * Asserts that the photo is removed
     */
    @AfterClass
    public void afterClass() {
        editPhotoPage.clickOnChangePhotoButton();
        editPhotoPage.getDeleteButton().click();
        editPhotoPage.waitUntilElementIsDisplayed(editPhotoPage.getProfileImagePlaceHolder());
        Assert.assertEquals(editPhotoPage.getPhotoHolderText(), "Add a photo");
    }
}
