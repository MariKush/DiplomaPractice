package tests;


import elements.HeaderElements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchResultPage;
import properties.ConfProperties;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterAndSortTest {

    public static WebDriver driver;

    public static HeaderElements headerElements;
    public static SearchResultPage searchResultPage;

    @BeforeAll
    public static void setUp() {
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
    void checkMinPriceFilter() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        headerElements.inputSearch(correctSearchWord);
        headerElements.clickSearchButton();
        Integer minPrice = 30000;
        searchResultPage = new SearchResultPage(driver);
        searchResultPage.inputMinPrice(minPrice.toString());
        searchResultPage.clickPriceFilterOKButton();
        assertTrue(!new SearchResultPage(driver).getGoodsPrices().isEmpty());
        for (WebElement webElement : new SearchResultPage(driver).getGoodsPrices()) {
            assertThat(Integer.parseInt(webElement.getText().replaceAll("\\s+","")), greaterThan(minPrice));
        }
    }


}
