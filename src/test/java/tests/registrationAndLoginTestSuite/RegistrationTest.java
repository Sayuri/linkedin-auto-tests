package tests.registrationAndLoginTestSuite;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RegistrationAndLoginPage;
import tests.BaseTest;

public class RegistrationTest extends BaseTest{

    /**
     * Data Provider that provides credentials for the registration tests
     * @return Object with credentials
     */
    @DataProvider(name = "credentials")
    public Object[][] searchTermsData() {
        return new Object[][]{
                {"", "", "", "", "Укажите имя", "Please enter your first name"},
                {"FirstName", "", "", "", "Укажите фамилию", "Please enter your last name"},
                {"FirstName", "LastName", "", "", "Укажите свой адрес электронной почты", "Please enter your email address"},
                {"FirstName", "LastName", "dem.iuliia.p2@gmail.com", "", "Укажите пароль", "Please enter your password"},
        };
    }

    /**
     * Checks the error message that should appear if some of the required field is left empty
     * @param firstName field for the first name
     * @param lastName field for the last name
     * @param email field for the email
     * @param password field for the password
     * @param expectedErrorMessageInRussian the error message in Russian that should appear if some of the required fields is left empty and is using the site in russian
     * @param expectedErrorMessageInEnglish the error message in Russian that should appear if some of the required fields is left empty and is using the site in english
     */
    @Test(dataProvider = "credentials")
    public void errorMessageOnEmptyFormFields(String firstName, String lastName, String email, String password,
                                              String expectedErrorMessageInRussian, String expectedErrorMessageInEnglish) {
        RegistrationAndLoginPage registrationAndLoginPage = new RegistrationAndLoginPage();
        registrationAndLoginPage.registrationFormFillAndSubmit(firstName, lastName, email, password);
        registrationAndLoginPage.emptyTheRegistrationForm();
        Assert.assertTrue(errorMassageIsEqualToActualMessage(registrationAndLoginPage.getEmptyFieldAlertMessage().getText(),
                new String[]{expectedErrorMessageInRussian, expectedErrorMessageInEnglish}),
                "Error message is not correct");
    }

    /**
     * Checks whether the actual message is equal to one of the expected messages
     * @param actualMessage the message that is displayed
     * @param expectedMessages the list of expected messages
     * @return true of the actual message is equal to one of the expected messages
     */
    public boolean errorMassageIsEqualToActualMessage(String actualMessage, String[] expectedMessages) {
        for(String expectedMessage : expectedMessages) {
            if(actualMessage.equals(expectedMessage)) {
                return true;
            }
        }
        return false;
    }
}
