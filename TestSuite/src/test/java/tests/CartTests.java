package tests;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CartTests extends BaseTest {


    @Test
    void checkAddToCartHeaderCounter() {
        searchPropertyAndWait("correctSearchWord");
        getSearchResultPage().clickAddToCartButton();
        assertEquals(getHeaderElement().getCountInChart(), "1");
    }

    @Test
    void checkItemInCart() {
        searchPropertyAndWait("correctSearchWord");
        String goodTitle = getSearchResultPage().getGoodsTitles().get(0).getText();
        Integer goodPrice = getSearchResultPage().getGoodsPrices().get(0);
        getSearchResultPage().clickAddToCartButton();
        getHeaderElement().clickOpenCartButton();
        getBaseElement().waitForPageReadyState();
        assertEquals(goodTitle, getCartPage().getGoodTitle());
        assertEquals(goodPrice, getCartPage().getGoodPrice());

    }

    @Test
    void checkSumAfterChangingQuantityOfGoods() {
        searchPropertyAndWait("correctSearchWord");
        Integer goodPrice = getSearchResultPage().getGoodsPrices().get(0);

        getSearchResultPage().clickAddToCartButton();
        getHeaderElement().clickOpenCartButton();
        getBaseElement().waitForPageReadyState();

        assertEquals(goodPrice, getCartPage().getSumPriceValue());

        getCartPage().clickAddOneMoreGoodButton();
        getBaseElement().waitForElementTextBeChanged(5, getCartPage().getSumPrice(), (goodPrice).toString());
        assertEquals(goodPrice * 2, getCartPage().getSumPriceValue());

        getCartPage().clickSubtractOneGoodButton();
        getBaseElement().waitForElementTextBePresented(5, getCartPage().getSumPrice(), goodPrice.toString());

        assertEquals(goodPrice, getCartPage().getSumPriceValue());
    }

    @Test
    void checkAddDifferentGoodsToChartsAndCheckPrice() {
        searchPropertyAndWait("correctSearchWord");
        Integer firstGoodPrice = getSearchResultPage().getGoodsPrices().get(0);
        Integer secondGoodPrice = getSearchResultPage().getGoodsPrices().get(1);

        getSearchResultPage().clickAddToCartButton();
        getSearchResultPage().clickSecondAddToCartButton();
        getHeaderElement().clickOpenCartButton();
        getBaseElement().waitForPageReadyState();

        assertEquals(firstGoodPrice + secondGoodPrice, getCartPage().getSumPriceValue());
    }

    @Test
    void checkDeleteGoodFromChart() {
        searchPropertyAndWait("correctSearchWord");

        getSearchResultPage().clickAddToCartButton();
        getHeaderElement().clickOpenCartButton();
        getBaseElement().waitForPageReadyState();

        getCartPage().clickGoodActionButton();
        getCartPage().waitForDeleteGoodButtonVisibility();
        getCartPage().clickDeleteGoodButton();

        getCartPage().checkEmptyChartIsPresented();
    }
}
