package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

import java.awt.*;
import java.io.File;


public class EditPhotoPage extends BasePage{

    @FindBy(css = "form[class='upload-form']>input[name='file']")
    private WebElement browseButton;

    @FindBy(css = "button.save")
    private WebElement saveButton;

    @FindBy(css = ".preview-profile.button-primary")
    private WebElement viewProfileAsButton;

    @FindBy(css = "img[src^='https://media.licdn.com/media']")
    private WebElement profileImage;

    @FindBy(id = "control_gen_3")
    private WebElement changePhotoButton;

    @FindBy(className = "delete")
    private WebElement deleteButton;

    @FindBy(css = "button#control_gen_3>span>span:first-child")
    private WebElement profileImagePlaceHolder;

    public EditPhotoPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsClickable(browseButton);
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }

    public WebElement getProfileImage() {
        return profileImage;
    }

    public WebElement getProfileImagePlaceHolder() {
        return profileImagePlaceHolder;
    }

    public void uploadAPhoto() {
        String thePath = new File(ClassLoader.getSystemResource("testPicture.jpg").getFile()).getAbsolutePath();
        browseButton.sendKeys(thePath);
        waitUntilElementIsClickable(saveButton);
        saveButton.click();
        waitUntilElementIsDisplayed(profileImage);
    }

    public String getPhotoHolderText() {
        return profileImagePlaceHolder.getText();
    }

    public void clickOnChangePhotoButton() {
        Point coordinates = changePhotoButton.getLocation();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseMove(coordinates.x,coordinates.y);
        changePhotoButton.click();
        waitUntilElementIsClickable(deleteButton);
        deleteButton.click();
    }
}
