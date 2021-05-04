package tests;


import com.google.common.collect.Comparators;
import org.junit.jupiter.api.Test;
import properties.ConfProperties;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterAndSortTest extends BaseTest {


    @Test
    void checkMinPriceFilter() {
        Integer minPrice = 30000;
        checkFilterAndSorting(
                () -> getSearchResultPage().inputMinPriceAndClickOkButton(minPrice.toString()),
                () -> {
                    for (Integer price : getSearchResultPage().getGoodsPrices()) {
                        assertThat(price, greaterThan(minPrice));
                    }
                });
    }


    @Test
    void checkMaxPriceFilter() {
        Integer maxPrice = 40000;
        checkFilterAndSorting(
                () -> getSearchResultPage().inputMaxPriceAndClickOkButton(maxPrice.toString()),
                () -> {
                    for (Integer price : getSearchResultPage().getGoodsPrices()) {
                        assertThat(price, lessThan(maxPrice));
                    }
                });
    }

    @Test
    void checkCheapFirstSortOption() {
        checkFilterAndSorting(
                () -> getSearchResultPage().chooseCheapFirstSortOption(),
                () -> assertTrue(Comparators.isInOrder(getSearchResultPage().getGoodsPrices(), Comparator.naturalOrder())));
    }


    @Test
    void checkExpensiveFirstSortOption() {
        checkFilterAndSorting(
                () -> getSearchResultPage().chooseExpensiveFirstSortOption(),
                () -> assertTrue(Comparators.isInOrder(getSearchResultPage().getGoodsPrices(), Comparator.reverseOrder())));
    }

    void checkFilterAndSorting(Runnable applyFilterAndSorting, Runnable consumerAllElements) {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();

        applyFilterAndSorting.run();

        getBaseElement().waitForPageReadyState();
        assertFalse(getSearchResultPage().getGoodsPrices().isEmpty());
        consumerAllElements.run();
    }
}
