import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderElements {


    public WebDriver driver;
    //локатор поля вводу для пошуку
    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchInput;
    //локатор кнопки "Знайти"
    @FindBy(xpath = "//button[contains(@class, ' search')]")
    private WebElement searchButton;

    public HeaderElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //метод введення значення в поле пошуку
    public void inputSearch(String value) {
        searchInput.sendKeys(value);
    }

    //метод натискання на кнопку "Пошук"
    public void clickSearchButton() {
        searchButton.click();
    }

}
