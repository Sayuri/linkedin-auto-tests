package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import static java.lang.Thread.sleep;

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

    @FindBy(id = "delete")
    private WebElement deleteButton;

    @FindBy(css = "button#control_gen_3>span>span:first-child")
    private WebElement profileImagePlaceHolder;

    public EditPhotoPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(browseButton, 5).isDisplayed();
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
        Actions action = new Actions(ConfigurationManager.getDriver());
        action.moveToElement(profileImage).moveToElement(changePhotoButton).click().build().perform();
    }
}
