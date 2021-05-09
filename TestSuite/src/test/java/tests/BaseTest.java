package tests;


import elements.BaseElement;
import elements.HeaderElements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.SearchResultPage;
import properties.ConfProperties;

import java.util.concurrent.TimeUnit;

public class BaseTest {


    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("homepage"));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    public HeaderElements getHeaderElement() {
        return new HeaderElements(driver);
    }

    public BaseElement getBaseElement() {
        return new BaseElement(driver);
    }

    public SearchResultPage getSearchResultPage() {
        return new SearchResultPage(driver);
    }

    public CartPage getCartPage() { return new CartPage(driver); }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    protected void searchPropertyAndWait(String property) {
        String correctSearchWord = ConfProperties.getProperty(property);
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();
    }

}
