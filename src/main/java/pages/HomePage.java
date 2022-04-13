package pages;

import core.DriverActions;
import org.apache.commons.math3.random.Well44497b;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import utilities.CommonUtils;
import utilities.Constants;
import utilities.DriverUtils;
import utilities.Report;

import java.util.List;

public class HomePage extends DriverActions {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static final String XPATH_SEARCH_LOCATOR = "//input[@id='twotabsearchtextbox']";
    private static final String XPATH_SEARCH_BUTTON = "//input[@id='nav-search-submit-button']";

    private static final String XPATH_AVG_CUSTOMER_REVIEW = "//span[contains(@class,'a-size-base') and text()='Customer Reviews']/ancestor::div[contains(@class,'a-row')]/div//div[@aria-label='4 Stars & Up']";
    private static final String XPATH_BRAND = "//span[contains(@class,'a-size-small') and text()='Brand']/ancestor::div[contains(@class,'sf-refinements-panel')]/div//span[text()='OnePlus']";
    private static final String XPATH_INTERNAL_MEMORY = "//span[contains(@class,'a-size-small') and text()='Internal Memory']/ancestor::div[contains(@class,'sf-refinements-panel')]/div//span[text()='128 GB']";
    private static final String XPATH_SORTBY = "//span[@class='a-dropdown-container']";
    private static final String XPATH_L2H = "//div[@class='a-popover-wrapper']//li/a[text()='Price: Low to High']";
    private static final String XPATH_PRODUCT_NAMES = "//div[contains(@class,'s-search-results')]//div[contains(@class,'s-list-col-right')]//div[contains(@class,'s-title-instructions-style')]//span[contains(@class,'a-text-normal')]";
    private static final String XPATH_PRICES = "//div[contains(@class,'s-search-results')]//div[contains(@class,'s-list-col-right')]//span[@class='a-price-whole']";
    private static final String XPATH_LINKS = "//div[contains(@class,'s-search-results')]//div[contains(@class,'s-list-col-right')]//div[contains(@class,'s-title-instructions-style')]//span[contains(@class,'a-text-normal')]/..";


    WebDriverWait wait = DriverUtils.waitExplicit(driver);


    public void navigateToHome() {

        DriverUtils.navigateToUrl(driver, Constants.URL);

    }

    public void searchForItem() {


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_SEARCH_LOCATOR)));
        WebElement search = getWebElement(XPATH_SEARCH_LOCATOR);
        setValue(XPATH_SEARCH_LOCATOR,Constants.searchName,"searching one plus 9");
        click(XPATH_SEARCH_BUTTON,"clicked search button");

    }

    public void applyFilters()
    {

        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(XPATH_AVG_CUSTOMER_REVIEW)));
        click(XPATH_AVG_CUSTOMER_REVIEW,"select avg customer filter");

        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(XPATH_BRAND)));
        click(XPATH_BRAND,"select brand filter");
//        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(XPATH_INTERNAL_MEMORY)));
//        click(XPATH_INTERNAL_MEMORY,"select internal memory");
        click(XPATH_SORTBY,"select sortby dropdown");
        click(XPATH_L2H,"select sortby value low to high");
    }

    public void getDetails()
    {
        List<WebElement> products = getElements(XPATH_PRODUCT_NAMES);
        System.out.println(products.size());
        List<WebElement> prices = getElements(XPATH_PRICES);
        List<WebElement> links = getElements(XPATH_LINKS);
        Report.pass("Successfully located the products");
        for(int i=0;i<products.size();i++)
        {
            String text = products.get(i).getText();

            if(text.contains("OnePlus")){
                System.out.println(text);
                System.out.println(prices.get(i).getText());
                System.out.println(links.get(i).getAttribute("href"));
            }
        }

    }
}
