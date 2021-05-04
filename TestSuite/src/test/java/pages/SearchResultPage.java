package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage {
    public WebDriver driver;
    //локатор, що містить всі заголовки знайдених товарів
    @FindBy(xpath = "//span[@class='goods-tile__title']")
    private List<WebElement> goodsTitles;

    //локатор, що містить повідомлення про відсутнысть результатів пошуку
    @FindBy(xpath = "//div[@class='catalog-empty']")
    private WebElement catalogEmpty;

    //локатор, для вводу мінімальної ціни
    @FindBy(xpath = "//input[@formcontrolname='min']")
    private WebElement minPriceInput;

    //локатор, для вводу максимальної ціни
    @FindBy(xpath = "//input[@formcontrolname='max']")
    private WebElement maxPriceInput;

    //Кнопка "ОК" для застосування фільтру по ціні
    @FindBy(xpath = "//button[contains(@class, ' slider-filter')]")
    private WebElement priceFilterOKButton;

    //локатор, що містить всі ціни знайдених товарів
    @FindBy(xpath = "//span[@class='goods-tile__price-value']")
    private List<WebElement> goodsPrices;


    public SearchResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<WebElement> getGoodsTitles() {
        return goodsTitles;
    }

    public boolean catalogEmptyMessageExist() {
        return catalogEmpty.isDisplayed();
    }

    //метод введення значення в поле мінімальної ціни
    public void inputMinPrice(String value) {
        minPriceInput.sendKeys(value);
    }

    //метод введення значення в поле максимальної ціни
    public void inputMaxPrice(String value) {
        maxPriceInput.sendKeys(value);
    }

    //метод натискання на кнопку "OK" фільтра ціни
    public void clickSearchButton() {
        priceFilterOKButton.click();
    }

    public List<WebElement> getGoodsPrices() {
        return goodsPrices;
    }

}
