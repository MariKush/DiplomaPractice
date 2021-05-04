package tests;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import properties.ConfProperties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilterAndSortTest extends BaseTest {


    @Test
    void checkMinPriceFilter() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();
        Integer minPrice = 30000;
        getSearchResultPage().inputMinPriceAndClickOkButton(minPrice.toString());
        assertFalse(getSearchResultPage().getGoodsPrices().isEmpty());
        for (WebElement webElement : getSearchResultPage().getGoodsPrices()) {
            assertThat(Integer.parseInt(webElement.getText().replaceAll("\\s+", "")), greaterThan(minPrice));
        }
    }


    @Test
    void checkMaxPriceFilter() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();
        Integer maxPrice = 40000;
        getSearchResultPage().inputMaxPriceAndClickOkButton(maxPrice.toString());
        assertFalse(getSearchResultPage().getGoodsPrices().isEmpty());
        for (WebElement webElement : getSearchResultPage().getGoodsPrices()) {
            assertThat(Integer.parseInt(webElement.getText().replaceAll("\\s+", "")), lessThan(maxPrice));
        }
    }
}
