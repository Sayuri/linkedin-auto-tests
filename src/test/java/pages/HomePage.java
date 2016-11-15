package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

/**
 * Home page of the logged in users
 */
public class HomePage extends BasePage{

    @FindBy(id = "identity")
    private WebElement identityBlock;

    @FindBy(id = "main-search-box")
    private WebElement searchField;

    @FindBy(className = "search-button")
    private WebElement searchButton;


    /**
     * The constructor of HomePage class that has PageFactory initialization of WebElements and explicit waiting
     * for identityBlock to be displayed
     */
    public HomePage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(identityBlock, 5).isDisplayed();
    }

    /**
     * Getter for WebElement identityBlock
     * @return WebElement identityBlock
     */
    public WebElement getIdentityBlock() {
        return identityBlock;
    }

    /**
     * Fills searchField with words for search and clicks on searchButton
     * @param wordsForSearch words that are put to the searchField and will be searched for
     * @return the new SearchPage object
     */
    public SearchPage search(String wordsForSearch) {
        searchField.sendKeys(wordsForSearch);
        searchButton.click();
        return new SearchPage();
    }

}
