package elements;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderElements extends BaseElement {


    public WebDriver driver;
    //локатор поля вводу для пошуку
    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchInput;
    //локатор кнопки "Знайти"
    @FindBy(xpath = "//button[contains(@class, ' search')]")
    private WebElement searchButton;
    @FindBy(xpath = "//span[contains(@class, 'counter')]")
    private WebElement cartCounter;
    @FindBy(xpath = "//button[contains(@class, 'header__button--active')]")
    private WebElement cartButton;

    public HeaderElements(WebDriver driver) {
        super(driver);
    }

    //метод введення значення в поле пошуку
    public void inputSearch(String value) {
        searchInput.sendKeys(value);
    }

    //метод натискання на кнопку "Пошук"
    public void clickSearchButton() {
        searchButton.click();
    }

    public void inputSearchAndClickSearchButton(String value) {
        inputSearch(value);
        clickSearchButton();
    }

    public String getCountInChart(){
        return cartCounter.getText();
    }

    public void clickOpenCartButton(){
        cartButton.click();
    }



}
