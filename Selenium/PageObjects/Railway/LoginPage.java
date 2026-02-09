package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {

    // Locators
    private final By txtEmail = By.xpath("//input[@id='username']");
    private final By txtPassword = By.xpath("//input[@id='password']");

    private final By btnLogin = By.xpath("//input[@value='login']");
    private final By btnForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");

    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']/parent::li[@class='selected']"));
    }

    public ForgotPasswordPage clickForgotPassword() {
        Utilities.click(btnForgotPassword);
        return new ForgotPasswordPage();
    }

    public String getLoginErrorMessage() {
        return Utilities.findElement(lblLoginErrorMsg).getText();
    }

    public <T extends GeneralPage> T login(String email, String password) {
        Utilities.findElement(txtEmail).sendKeys(email);
        Utilities.findElement(txtPassword).sendKeys(password);

        WebElement btnLogin = Utilities.findElement(this.btnLogin);
        Utilities.click(this.btnLogin);

        // Wait for form processing
        WaitUtils.waitForElementStale(btnLogin);
        if(this.isPageShown()) {
            return (T) this;
        } else {
            return (T) new HomePage();
        }
    }

    public HomePage expectSuccess() {
        return new HomePage();
    }

    public LoginPage expectFailure() {
        WaitUtils.waitForElementVisible(lblLoginErrorMsg);
        return this;
    }
}


