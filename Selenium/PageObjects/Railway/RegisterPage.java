package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.By;

public class RegisterPage extends GeneralPage {

    // Locators
    private final By txtEmailLocator = By.xpath("//input[@id='email']");
    private final By txtPasswordLocator = By.xpath("//input[@id='password']");
    private final By txtConfirmPasswordLocator = By.xpath("//input[@id='confirmPassword']");
    private final By txtPidLocator = By.xpath("//input[@id='pid']");

    private final By lblPasswordErrorMsgLocator = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By lblPidErrorMsgLocator = By.xpath("//label[@for='pid' and @class='validation-error']");

    private final By btnRegisterLocator = By.xpath("//input[@value='Register']");
    private final By lblRegisterErrorMsgLocator = By.xpath("//p[@class='message error']");

    // Methods
    public String getRegisterErrorMsg() {
        return Utilities.findElement(lblRegisterErrorMsgLocator).getText();
    }

    public String getPasswordErrorMsg() {
        return Utilities.findElement(lblPasswordErrorMsgLocator).getText();
    }

    public String getPidErrorMsg() {
        return Utilities.findElement(lblPidErrorMsgLocator).getText();
    }

    public RegisterPage register(String email, String password, String confirmPassword, String pid) {
        Utilities.findElement(txtPasswordLocator).sendKeys(password);
        Utilities.findElement(txtEmailLocator).sendKeys(email);
        Utilities.findElement(txtPidLocator).sendKeys(pid);
        Utilities.findElement(txtConfirmPasswordLocator).sendKeys(confirmPassword);

        Utilities.waitForElementClickable(Constant.WEBDRIVER, btnRegisterLocator);

        Utilities.findElement(btnRegisterLocator).click();
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


