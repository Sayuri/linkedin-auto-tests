package tests.profileInformationEditing;

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
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
