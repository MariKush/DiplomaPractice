package tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import properties.ConfProperties;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTests extends BaseTest {


    @Test
    void checkThatUrlContainsSearchWord() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        assertThat(getURL(), containsString(correctSearchWord));
    }

    @Test
    void checkThatSearchResultsContainsSearchWord() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();
        for (WebElement webElement : getSearchResultPage().getGoodsTitles()) {
            assertThat(webElement.getText(), containsStringIgnoringCase(correctSearchWord));
        }
    }

    @Test
    void checkThatSearchWithWrongSearchWord() {
        String wrongSearchWord = ConfProperties.getProperty("wrongSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(wrongSearchWord);
        getBaseElement().waitForPageReadyState();
        Assertions.assertTrue(getSearchResultPage().catalogEmptyMessageExist());
    }

}
