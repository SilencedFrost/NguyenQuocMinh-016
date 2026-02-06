package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import org.openqa.selenium.By;

public class LoginPage extends GeneralPage {

    // Locators
    private final By txtEmailLocator = By.xpath("//input[@id='username']");
    private final By txtPasswordLocator = By.xpath("//input[@id='password']");

    private final By btnLoginLocator = By.xpath("//input[@value='login']");
    private final By btnForgotPasswordLocator = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");

    private final By lblLoginErrorMsgLocator = By.xpath("//p[@class='message error LoginForm']");

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']/parent::li[@class='selected']"));
    }

    public ForgotPasswordPage clickForgotPassword() {
        Utilities.click(btnForgotPasswordLocator);
        return new ForgotPasswordPage();
    }

    public String getLoginErrorMessage() {
        return Utilities.findElement(lblLoginErrorMsgLocator).getText();
    }

    public LoginPage login(String email, String password) {
        Utilities.findElement(txtEmailLocator).sendKeys(email);
        Utilities.findElement(txtPasswordLocator).sendKeys(password);

        Utilities.click(btnLoginLocator);
        return this;
    }

    public HomePage expectSuccess() {
        return new HomePage();
    }

    public LoginPage expectFailure() {
        WaitUtils.waitForElementVisible(lblLoginErrorMsgLocator);
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


