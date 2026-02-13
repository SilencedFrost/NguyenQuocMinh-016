package PageObjects.AutomationExercise;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends GeneralPage {

    // Locators
    private final By btnCheckout = By.xpath("//a[contains(@class, 'check_out')]");

    // Methods
    public CheckoutPage gotoCheckout() {
        WebElement checkoutButton = Utilities.click(btnCheckout);
        // If button is still present
        if(!Utilities.isElementStale(checkoutButton)) {
            // disableAd
            Utilities.disableGoogleAd();
            // Wait for element stale (page redirects)
            WaitUtils.waitForElementStale(checkoutButton);
        }
        WaitUtils.waitForPageLoad();
        return new CheckoutPage();
    }
}
