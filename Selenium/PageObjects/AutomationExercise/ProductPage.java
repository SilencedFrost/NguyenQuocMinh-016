package PageObjects.AutomationExercise;

import Common.Common.RandomUtils;
import Common.Common.Utilities;
import Common.Common.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage extends GeneralPage{

    // Locators
    private final By btnViewProduct = By.xpath("//div[@class='product-image-wrapper']//a[.='View Product']");
    private final By btnAddToCart = By.xpath("//div[contains(@class, 'productinfo')]//a[contains(@class, 'add-to-cart')]");
    private final By btnViewCart = By.xpath("//div[@id='cartModal']//a[@href='/view_cart']");
    private static final String btnViewProductIndexXpath = "(//div[@class='product-image-wrapper']//a[.='View Product'])[%d]";
    private static final String btnAddToCartIndexXpath = "(//div[contains(@class, 'productinfo')]//a[contains(@class, 'add-to-cart')])[%d]";

    // Methods
    public ProductDetailPage viewRandomProduct() {
        int totalButtons = Utilities.findElements(btnViewProduct).size();
        WebElement btnSelectedProduct = Utilities.click(By.xpath(String.format(btnViewProductIndexXpath, RandomUtils.randomInt(totalButtons))));
        // If button is still present
        if(!Utilities.isElementStale(btnSelectedProduct)) {
            // Disable Ad
            Utilities.disableGoogleAd();
            // Wait for element stale (page redirects)
            WaitUtils.waitForElementStale(btnSelectedProduct);
        }
        WaitUtils.waitForPageLoad();
        return new ProductDetailPage();
    }

    public ProductPage addRandomProductToCart() {
        int totalButtons = Utilities.findElements(btnAddToCart).size();
        WebElement btnSelectedProduct = Utilities.click(By.xpath(String.format(btnAddToCartIndexXpath, RandomUtils.randomInt(totalButtons))));
        return this;
    }

    public CartPage viewCart() {
        WebElement viewCartButton = Utilities.click(btnViewCart);

        // If button is still present
        if(!Utilities.isElementStale(viewCartButton)) {
            // Disable Ad
            Utilities.disableGoogleAd();
            // Wait for element stale (page redirects)
            WaitUtils.waitForElementStale(viewCartButton);
        }
        WaitUtils.waitForPageLoad();
        return new CartPage();
    }
}
