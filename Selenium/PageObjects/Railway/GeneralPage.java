package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.MenuItem;
import org.openqa.selenium.By;

public abstract class GeneralPage {

    //Locators
    private final By tabHomeLocator = By.xpath("//div[@id='menu']//a[@href='../']");
    private final By tabFaqLocator = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
    private final By tabRegisterLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabLoginLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogoutLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");

    private final By lblWelcomeMessageLocator = By.xpath("//div[@class='account']/strong");

    // Methods
    abstract boolean isPageShown();

    public String getWelcomeMessage() {
        return Utilities.findElement(lblWelcomeMessageLocator).getText();
    }

    public GeneralPage gotoPage(MenuItem menuItem) {
        return switch (menuItem) {
            case HOME -> {
                Utilities.click(tabHomeLocator);
                yield new HomePage();
            }
            case FAQ -> {
                Utilities.click(tabFaqLocator);
                yield new FaqPage();
            }
            case REGISTER -> {
                Utilities.click(tabRegisterLocator);
                yield new RegisterPage();
            }
            case LOGIN -> {
                Utilities.click(tabLoginLocator);
                yield new LoginPage();
            }
        };
    }

    public HomePage clickLogout() {
        Utilities.click(tabLogoutLocator);
        return new HomePage();
    }

//    public By getTabLoginLocator() {
//        return this.tabLoginLocator;
//    }

    public By getTabLogoutLocator() {
        return this.tabLogoutLocator;
    }

//    public By getTabRegisterLocator() {
//        return this.tabRegisterLocator;
//    }

    public By getLblWelcomeMessageLocator() {
        return this.lblWelcomeMessageLocator;
    }
}
