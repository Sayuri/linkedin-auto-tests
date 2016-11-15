package tests.searchTextInTitlesTestSuite;


import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegistrationAndLoginPage;
import pages.SearchPage;
import tests.BaseTest;

public class SearchFromHomeTest extends BaseTest {

    private HomePage homePage;
    private RegistrationAndLoginPage registrationAndLoginPage;

    /**
     * Data Provider that provides with the words for search and words that the found ones are compared with
     * @return Object with words wor search and  words that the found ones are compared with
     */
    @DataProvider(name = "searchTerms")
    public Object[][] searchTermsData() {
        return new Object[][] {
                {"HR", "HR"},
                {"hr", "HR"}
        };
    }

    /**
     * Method that is run before the class in which registration page is created, valid credentials are put, signIn
     * button is pressed, new HomePage object is received
     */
    @BeforeClass
    public void beforeTest() {
        RegistrationAndLoginPage registrationAndLoginPage = new RegistrationAndLoginPage();
        homePage = registrationAndLoginPage.loginAndGoToHomePage("dem.iuliia.p@gmail.com", "dem.iuliia.p.password");
    }

    /**
     * Checks whether the search words are present in the found persons titles
     * @param searchTerm the words for search
     * @param expectedString the words that the persons titles are compared with
     */
    @Test(dataProvider = "searchTerms")
    public void checkThatSearchedTextIsPresentInTitle(String searchTerm, String expectedString) {
        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertTrue(searchPage.areSearchedWordsPresentInPersonsTitles(expectedString));
        homePage = searchPage.returnToHomePage();
    }
}
