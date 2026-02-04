package PageObjects.Railway;

import Common.Constant.Constant;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class GeneralPage {

    //Locators
    private final By _tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By _tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By _lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

    //Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(_tabLogin);
    }

    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(_tabLogout);
    }

    public WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(_lblWelcomeMessage);
    }

    // Methods
    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }
}
