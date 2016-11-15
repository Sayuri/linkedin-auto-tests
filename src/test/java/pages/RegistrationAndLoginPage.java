package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

/**
 * Home page of not logged in users
 */
public class RegistrationAndLoginPage extends BasePage{

    @FindBy(id="reg-firstname")
    private WebElement firstNameField;

    @FindBy(id="reg-lastname")
    private WebElement lastNameField;

    @FindBy(id="reg-email")
    private WebElement emailField;

    @FindBy(id="reg-password")
    private WebElement passwordField;

    @FindBy(id="registration-submit")
    private WebElement joinNowButton;

    @FindBy(xpath = "//span[@class='alert-content']")
    private WebElement emptyFieldAlertMessage;

    @FindBy(id = "login-email")
    private WebElement loginEmailField;

    @FindBy(id = "login-password")
    private WebElement loginPasswordField;

    @FindBy(id = "login-submit")
    private WebElement signInButton;

    /**
     * Getter for WebElement emptyFieldAlertMessage
     * @return WebElement emptyFieldAlertMessage
     */
    public WebElement getEmptyFieldAlertMessage() {
        return emptyFieldAlertMessage;
    }

    /**
     * The constructor of RegistrationAndLoginPage class that has PageFactory initialization of WebElements in it and explicit waiting
     * for loginEmailField, loginPasswordField and signInButton to be displayed
     */
    public RegistrationAndLoginPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(loginEmailField);
        waitUntilElementIsDisplayed(loginPasswordField);
        waitUntilElementIsDisplayed(signInButton);
    }

    /**Fills loginEmailField and loginPasswordField with valid email and password and clicks on signInButton
     * @param email registered email for logging in
     * @param password valid password for logging in
     * @return new HomePage object
     */
    public HomePage loginAndGoToHomePage(String email, String password) {
        loginEmailField.sendKeys(email);
        loginPasswordField.sendKeys(password);
        signInButton.click();
        return new HomePage();
    }

    /**Fills firstNameField, lastNameField, emailField and passwordField with valid first name, last name, email and
     * password and clicks on joinNowButton
     * @param firstName first name for registration
     * @param lastName last name for registration
     * @param email email for registration
     * @param password password for registration
     */
    public void registrationFormFillAndSubmit(String firstName, String lastName, String email, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        joinNowButton.click();
    }

    /**
     * Clears the content of WebElements firstNameField, lastNameField, emailField and passwordField
     */
    public void emptyTheRegistrationForm() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        passwordField.clear();
    }

}
