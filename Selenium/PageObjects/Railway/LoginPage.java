package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {

    // Locators
    private final By txtEmailLocator = By.xpath("//input[@id='username']");
    private final By txtPasswordLocator = By.xpath("//input[@id='password']");
    private final By btnLoginLocator = By.xpath("//input[@value='login']");
    private final By lblLoginErrorMsgLocator = By.xpath("//p[@class='message error LoginForm']");

    // Methods
    public String getLoginErrorMessage() {
        return Utilities.findElement(lblLoginErrorMsgLocator).getText();
    }

    public LoginPage login(String email, String password) {
        Utilities.findElement(txtEmailLocator).sendKeys(email);
        Utilities.findElement(txtPasswordLocator).sendKeys(password);

        Utilities.waitForElementClickable(Constant.WEBDRIVER, btnLoginLocator);

        Utilities.findElement(btnLoginLocator).click();
        return this;
    }

    public HomePage expectSuccess() {
        return new HomePage();
    }

    public LoginPage expectFailure() {
        Utilities.waitForElementVisibility(Constant.WEBDRIVER, lblLoginErrorMsgLocator);
        return this;
    }

//    public By getTxtEmailLocator() {
//        return this.txtEmailLocator;
//    }
//
//    public By getTxtPasswordLocator() {
//        return this.txtPasswordLocator;
//    }
//
//    public By getBtnLoginLocator() {
//        return this.btnLoginLocator;
//    }
//
//    public By getLblLoginErrorMsgLocator() {
//        return this.lblLoginErrorMsgLocator;
//    }
}


