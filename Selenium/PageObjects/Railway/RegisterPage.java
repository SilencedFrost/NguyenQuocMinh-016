package PageObjects.Railway;

import Common.Common.Utilities;
import org.openqa.selenium.By;

public class RegisterPage extends GeneralPage {

    // Locators
    private final By txtEmail = By.xpath("//input[@id='email']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By txtPid = By.xpath("//input[@id='pid']");

    private final By lblPasswordErrorMsg = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By lblPidErrorMsg = By.xpath("//label[@for='pid' and @class='validation-error']");
    private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
    private final By lblTitle = By.xpath("//div[@id='content']/h1");
    private final By lblConfirmMessage = By.xpath("//div[@id='content']/p");

    private final By btnRegister = By.xpath("//input[@value='Register']");

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']/parent::li[@class='selected']"));
    }

    public String getRegisterErrorMsg() {
        return Utilities.findElement(lblRegisterErrorMsg).getText();
    }

    public String getPasswordErrorMsg() {
        return Utilities.findElement(lblPasswordErrorMsg).getText();
    }

    public String getPidErrorMsg() {
        return Utilities.findElement(lblPidErrorMsg).getText();
    }

    public String getTitle() {
        return Utilities.findElement(lblTitle).getText();
    }

    public String getConfirmMsg() {
        return Utilities.findElement(lblConfirmMessage).getText();
    }

    public RegisterPage register(String email, String password, String confirmPassword, String pid) {
        Utilities.findElement(txtPassword).sendKeys(password);
        Utilities.findElement(txtEmail).sendKeys(email);
        Utilities.findElement(txtPid).sendKeys(pid);
        Utilities.findElement(txtConfirmPassword).sendKeys(confirmPassword);

        Utilities.click(btnRegister);
        return this;
    }

    public RegisterPage register(String email, String password, String pid) {
        return this.register(email, password, password, pid);
    }

//    public By getTxtUsernameLocator() {
//        return this.txtUsernameLocator;
//    }
//
//    public By getTxtPasswordLocator() {
//        return this.txtPasswordLocator;
//    }
//
//    public By getTxtConfirmPasswordLocator() {
//        return this.txtConfirmPasswordLocator;
//    }
//
//    public By getTxtPidLocator() {
//        return this.txtPidLocator;
//    }
//
//    public By getBtnRegisterLocator() {
//        return this.btnRegisterLocator;
//    }
//
//    public By getLblRegisterErrorMsgLocator() {
//        return this.lblRegisterErrorMsgLocator;
//    }
}


