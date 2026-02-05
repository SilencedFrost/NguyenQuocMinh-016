package PageObjects.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralPage {

    //Locators
    private final By tabLoginLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogoutLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabRegisterLocator = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabFaqLocator = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
    private final By lblWelcomeMessageLocator = By.xpath("//div[@class='account']/strong");

    //Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLoginLocator);
    }

    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogoutLocator);
    }

    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegisterLocator);
    }

    protected WebElement getTabFaq() {
        return Constant.WEBDRIVER.findElement(tabFaqLocator);
    }

    protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessageLocator);
    }

    // Methods
    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public HomePage clickLogout() {
        this.getTabLogout().click();
        return new HomePage();
    }

    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }

    public FaqPage gotoFaqPage() {
        this.getTabFaq().click();
        return new FaqPage();
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
