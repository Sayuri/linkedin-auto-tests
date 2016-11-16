package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @FindBy(css = ".profile-picture img")
    private WebElement imageBlock;

//    src="https://media.licdn.com/media/AAEAAQAAAAAAAAf9AAAAJDM3NTBhMWJhLWE1YjQtNGQ2ZS04MDEyLWM0M2U4NmJhZjBhMw.jpg"
//    src="https://media.licdn.com/media/AAEAAQAAAAAAAAhPAAAAJDk4OGU2ZTc0LTI2NzMtNGJhNi05ZDllLTI2MTEyYjQxYzI2OQ.jpg"
//
//    src="https://static.licdn.com/scds/common/u/images/themes/katy/ghosts/person/ghost_person_200x200_v1.png"

    public EditPhotoPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(browseButton, 5).isDisplayed();
    }

    public void uploadAPhoto() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String thePath = new File(ClassLoader.getSystemResource("testPicture.jpg").getFile()).getAbsolutePath();
        browseButton.sendKeys(thePath);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(imageBlock.getAttribute("src"));
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(imageBlock.getAttribute("src"));
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
