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

}
