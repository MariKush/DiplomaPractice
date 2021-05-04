import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTests {


    public static WebDriver driver;

    public static HeaderElements headerElements;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        headerElements = new HeaderElements(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("homepage"));
    }

    @AfterAll
    static void tearDown() {
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
        assertThat(driver.getCurrentUrl(), containsString(correctSearchWord));
    }


}
