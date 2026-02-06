package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Railway.MenuItem;
import org.openqa.selenium.By;

public abstract class GeneralPage {

    // Locators
    private final String genericTabLocatorString = "//div[@id='menu']//a[@href='%s']";
    private final By tabLogoutLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");

    private final By lblWelcomeMessageLocator = By.xpath("//div[@class='account']/strong");

    // Methods
    abstract boolean isPageShown();

    public String getWelcomeMessage() {
        return Utilities.findElement(lblWelcomeMessageLocator).getText();
    }

    public GeneralPage gotoPage(MenuItem menuItem) {
        Utilities.click(By.xpath(String.format(genericTabLocatorString, menuItem.getHref())));
        return switch (menuItem) {
            case HOME -> new HomePage();
            case FAQ -> new FaqPage();
            case BOOK_TICKET -> new BookTicketPage();
            case REGISTER -> new RegisterPage();
            case LOGIN -> new LoginPage();
        };
    }

    public HomePage clickLogout() {
        Utilities.click(tabLogoutLocator);
        return new HomePage();
    }

    public By getTabLogoutLocator() {
        return this.tabLogoutLocator;
    }
}
