package PageObjects.AutomationExercise;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import Common.Constant.AutomationExercise.MenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GeneralPage {

    // Locators
    private static final String genericTabXpath = "//ul[@class='nav navbar-nav']/li/a[contains(., '%s')]";

    // Methods
    public GeneralPage gotoPage(MenuItem menuItem) {

        WebElement clickedButton = Utilities.click(By.xpath(String.format(genericTabXpath, menuItem.getText())));
        // If button is still present
        if(!Utilities.isElementStale(clickedButton)) {
            // disableAd
            Utilities.disableGoogleAd();
            // Wait for element stale (page redirects)
            WaitUtils.waitForElementStale(clickedButton);
        }
        // Wait for page reload to complete
        WaitUtils.waitForPageLoad();
        return switch (menuItem) {
            case HOME -> new HomePage();
            case PRODUCTS -> new ProductPage();
            case SIGNUP_LOGIN -> new SignupLoginPage();
        };
    }
}
