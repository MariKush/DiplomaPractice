package tests;


import org.junit.jupiter.api.Test;
import properties.ConfProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CartTests extends BaseTest{

    @Test
    void checkAddToCartHeaderCounter() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();
        getSearchResultPage().clickAddToCartButton();
        assertEquals(getHeaderElement().getCountInChart(), "1");
    }

    @Test
    void checkItemInCart() {
        String correctSearchWord = ConfProperties.getProperty("correctSearchWord");
        getHeaderElement().inputSearchAndClickSearchButton(correctSearchWord);
        getBaseElement().waitForPageReadyState();
        getSearchResultPage().clickAddToCartButton();

    }
}
