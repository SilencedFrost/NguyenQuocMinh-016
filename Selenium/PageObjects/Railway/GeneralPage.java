package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.MenuItem;
import org.openqa.selenium.By;

public class GeneralPage {

    //Locators
    private final By tabHomeLocator = By.xpath("//a[@href='../']");
    private final By tabFaqLocator = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
    private final By tabRegisterLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabLoginLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogoutLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");

    private final By lblWelcomeMessageLocator = By.xpath("//div[@class='account']/strong");

    // Methods
    public String getWelcomeMessage() {
        return Utilities.findElement(lblWelcomeMessageLocator).getText();
    }

    public GeneralPage gotoPage(MenuItem menuItem) {
        return switch (menuItem) {
            case HOME -> {
                Utilities.findElement(tabHomeLocator).click();
                yield new HomePage();
            }
            case FAQ -> {
                Utilities.findElement(tabFaqLocator).click();
                yield new FaqPage();
            }
            case REGISTER -> {
                Utilities.findElement(tabRegisterLocator).click();
                yield new RegisterPage();
            }
            case LOGIN -> {
                Utilities.findElement(tabLoginLocator).click();
                yield new LoginPage();
            }
        };
    }

    public HomePage clickLogout() {
        Utilities.findElement(tabLogoutLocator).click();
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
