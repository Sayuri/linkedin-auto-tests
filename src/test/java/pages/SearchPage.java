package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationManager;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Page that is displayed after searching through search form on the HomePage
 */
public class SearchPage extends BasePage {

    @FindBy(xpath = "//ol[@id='results']/li[contains(@class, 'people')]")
    private List<WebElement> listOfFoundPeople;

    @FindBy(id = "results-container")
    private WebElement resultContainer;

    @FindBy(xpath = "//ul[@class = 'pagination']/li/a")
    private List<WebElement> listOfPagesNumbers;

    @FindBy(className = "search-info")
    private WebElement searchInfoLine;

    @FindBy(xpath = "//a[@href = '/home?trk=nav_responsive_tab_home']")
    private WebElement linkToHomePage;

    public SearchPage() {
        PageFactory.initElements(ConfigurationManager.getDriver(), this);
        waitUntilElementIsDisplayed(resultContainer);
    }

    /**
     * Getter for WebElement getListOfPagesNumbers
     * @return WebElement getListOfPagesNumbers
     */
    public List<WebElement> getListOfPagesNumbers() {
        return listOfPagesNumbers;
    }

    /**
     * Getter for WebElement getLinkToHomePage
     * @return WebElement getLinkToHomePage
     */
    public WebElement getLinkToHomePage() {
        return linkToHomePage;
    }

    /**
     * Getter for WebElement getListOfFoundPeople
     * @return WebElement getListOfFoundPeople
     */
    public List<WebElement> getListOfFoundPeople() {
        return listOfFoundPeople;
    }

    /**
     * Searches for WebElement that contains the text
     * @param person WebElement to get the title from
     * @return the text of WebElement
     */
    public String getThePersonsTitle(WebElement person) {
        return person.findElement(By.xpath("//div[@class='description']/b")).getText();
    }

    /**
     * @param pageFrom the first page that the titles are took from
     * @param pageTo the last page that the titles are took from
     * @param wordsToSearch the text that is compared with the titles
     * @return true if the text passed to the methos is present in all the titles
     */
    public boolean areSearchedWordsPresentInPersonsTitles(int pageFrom, int pageTo, String wordsToSearch) {
        SearchPage searchPage = this;
        for (int i = pageFrom; i < pageTo; i++) {
            searchPage.getThePageNumberButton(searchPage.getListOfPagesNumbers(), i).click();
            searchPage = new SearchPage();
            searchPage.waitUntilPageIsLoaded();
            searchPage.isFoundTitlesMatchTheSearchWords(searchPage.getListOfFoundPeople(), wordsToSearch);
        }
        return true;
    }

    /**
     * @param wordsToSearch the text that is compared with the titles
     * @return true if the text passed to the methos is present in all the titles
     */
    public boolean areSearchedWordsPresentInPersonsTitles(String wordsToSearch) {
        return isFoundTitlesMatchTheSearchWords(this.getListOfFoundPeople(), wordsToSearch);
    }

    /**
     * @param listOfPagesNumbers list of WebElements that are buttons of page numbers
     * @param pageNumber the number is searched for in the list of WebElements that are buttons of the page numbers
     * @return
     */
    private WebElement getThePageNumberButton(List<WebElement> listOfPagesNumbers, int pageNumber) {
        for (WebElement element : listOfPagesNumbers) {
            if(!element.getText().equals("< Prev") && Integer.parseInt(element.getText()) == pageNumber) {
                return element;
            }
        }
        return null;
    }

    /**
     * Waits for the loading gif to appear and then to disappear
     */
    private void waitUntilPageIsLoaded() {
        String script = "return window.getComputedStyle(document.querySelector('#voltron-overlay'),':after').getPropertyValue('content')";
        JavascriptExecutor js = (JavascriptExecutor) ConfigurationManager.getDriver();
        String content = (String) js.executeScript(script);
        waitUntilLoadingGifAppears(content);
        waitUntilLoadingGifDisappears(content);
    }

    /**Compares the searchWords with the persons titles
     * @param listOfFoundPeople list of WebElements that represent found people
     * @param searchWords words that are comoared with persons titles
     * @return
     */
    private boolean isFoundTitlesMatchTheSearchWords(List<WebElement> listOfFoundPeople, String searchWords) {
        for (WebElement person : listOfFoundPeople) {
            if (!this.getThePersonsTitle(person).equals(searchWords.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Waits until loading gif appears
     * @param content the String that represents state of the WebElement that is a loading gif
     */
    private void waitUntilLoadingGifAppears(String content) {
        for(int i = 0; i < 10; i++) {
            if(content.equals(" ")) {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            } else {
                return;
            }
        }
    }

    /**
     * Waits until loading gif disappears
     * @param content the String that represents state of the WebElement that is a loading gif
     */
    private void waitUntilLoadingGifDisappears(String content) {
        for(int i = 0; i < 10; i++) {
            if(!content.equals(" ")) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            } else {
                return;
            }
        }
    }

    /**
     * Clicks on the Home button
     * @return new HomePage object
     */
    public HomePage returnToHomePage() {
        getLinkToHomePage().click();
        return new HomePage();
    }

}
