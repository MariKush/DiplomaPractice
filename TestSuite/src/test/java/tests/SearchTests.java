package tests;


import elements.HeaderElements;
import pages.SearchResultPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import properties.ConfProperties;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTests {


    public static WebDriver driver;

    public static HeaderElements headerElements;
    public static SearchResultPage searchResultPage;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        headerElements = new HeaderElements(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("homepage"));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    void checkThatUrlContainsSearchWord() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        headerElements.inputSearch(correctSearchWord);
        headerElements.clickSearchButton();
        assertThat(driver.getCurrentUrl(), containsString(correctSearchWord));
    }

    @Test
    void checkThatSearchResultsContainsSearchWord() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        headerElements.inputSearch(correctSearchWord);
        headerElements.clickSearchButton();
        for (WebElement webElement : new SearchResultPage(driver).getGoodsTitles()) {
            assertThat(webElement.getText(), containsStringIgnoringCase(correctSearchWord));
        }
    }

    @Test
    void checkThatSearchWithWrongSearchWord() {
        String wrongSearchWord = ConfProperties.getProperty("wrongSearchWord");
        headerElements.inputSearch(wrongSearchWord);
        headerElements.clickSearchButton();
        Assertions.assertTrue(new SearchResultPage(driver).getGoodsTitles().isEmpty());
        Assertions.assertTrue(new SearchResultPage(driver).catalogEmptyMessageExist());

    }

}
