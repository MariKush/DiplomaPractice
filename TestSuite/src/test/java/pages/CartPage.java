package pages;


import elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CartPage extends BaseElement {


    public WebDriver driver;

    @FindBy(xpath = "//a[@class='cart-product__title']")
    private WebElement goodTitle;
    @FindBy(xpath = "//p[@class='cart-product__price']")
    private WebElement goodPrice;
    @FindBy(xpath = "//input[contains(@class, 'cart-counter')]")
    private WebElement cartCounter;
    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']/span[1]")
    private WebElement sumPrice;
    @FindBy(xpath = "//button[contains(@class, 'cart-counter__button')][2]")
    private WebElement addOneMoreGood;
    @FindBy(xpath = "//button[contains(@class, 'cart-counter__button')][1]")
    private WebElement subtractOneGood;
    @FindBy(xpath = "//button[@id='cartProductActions0']")
    private WebElement goodActionButton;
    @FindBy(xpath = "//button[contains(@class, 'actions')]")
    private WebElement deleteGoodButton;
    @FindBy(xpath = "//div[contains(@class, 'cart-dummy')]")
    private WebElement emptyChart;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSumPrice() {
        return sumPrice;
    }

    public String getGoodTitle() { return goodTitle.getText(); }

    public Integer getGoodPrice() {
        return Integer.parseInt(goodPrice.getText().replaceAll("[^0-9.]", ""));
    }

    public void clickAddOneMoreGoodButton() {
        addOneMoreGood.click();
    }

    public void clickSubtractOneGoodButton() {
        subtractOneGood.click();
    }

    public void clickGoodActionButton() {
        goodActionButton.click();
    }

    public void clickDeleteGoodButton() {
        deleteGoodButton.click();
    }

    public Integer getSumPriceValue() {
        return Integer.parseInt(sumPrice.getText());
    }

    public void waitForDeleteGoodButtonVisibility() { waitForElementVisibility(5, deleteGoodButton); }

    public void checkEmptyChartIsPresented() { waitForElementVisibility(5, emptyChart);
    }
}
