package tests;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import properties.ConfProperties;

import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilterAndSortTest extends BaseTest {


    @Test
    void checkMinPriceFilter() {
        Integer minPrice = 30000;
        checkFilterAndSorting(
                () -> getSearchResultPage().inputMaxPriceAndClickOkButton(minPrice.toString()),
                (webElements) -> {
                    for (WebElement webElement : webElements) {
                        assertThat(Integer.parseInt(webElement.getText().replaceAll("\\s+", "")), greaterThan(minPrice));
                    }
                });
    }


    @Test
    void checkMaxPriceFilter() {
        Integer maxPrice = 40000;
        checkFilterAndSorting(
                () -> getSearchResultPage().inputMaxPriceAndClickOkButton(maxPrice.toString()),
                (webElements) -> {
                    for (WebElement webElement : webElements) {
                        assertThat(Integer.parseInt(webElement.getText().replaceAll("\\s+", "")), lessThan(maxPrice));
                    }
                });
    }

    void checkFilterAndSorting(Runnable applyFilterAndSorting, Consumer<List<WebElement>> consumerAllElements) {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();

        applyFilterAndSorting.run();

        assertFalse(getSearchResultPage().getGoodsPrices().isEmpty());
        consumerAllElements.accept(getSearchResultPage().getGoodsPrices());

    }
}
