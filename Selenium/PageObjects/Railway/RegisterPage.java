package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPage extends GeneralPage {

    // Locators
    private final By txtUsernameLocator = By.xpath("//input[@id='email']");
    private final By txtPasswordLocator = By.xpath("//input[@id='password']");
    private final By txtConfirmPasswordLocator = By.xpath("//input[@id='confirmPassword']");
    private final By txtPidLocator = By.xpath("//input[@id='pid']");
    private final By btnRegisterLocator = By.xpath("//input[@value='Register']");
    private final By lblRegisterErrorMsgLocator = By.xpath("//p[@class='message error']");

    // Elements
    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtUsernameLocator);
    }

    protected WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPasswordLocator);
    }

    protected WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPasswordLocator);
    }

    protected WebElement getTxtPid() {
        return Constant.WEBDRIVER.findElement(txtPidLocator);
    }

    protected WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegisterLocator);
    }

    protected WebElement getLblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblRegisterErrorMsgLocator);
    }

    // Methods
    public RegisterPage register(String email, String password, String confirmPassword, String pid) {
        this.getTxtEmail().sendKeys(email);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        this.getTxtPassword().sendKeys(password);
        this.getTxtPid().sendKeys(pid);

        Utilities.waitForElementClickable(Constant.WEBDRIVER, btnRegisterLocator);

        this.getBtnRegister().click();
        return this;
    }

    public RegisterPage register(String email, String password, String pid) {
        return this.register(email, password, password, pid);
    }

    public By getTxtUsernameLocator() {
        return this.txtUsernameLocator;
    }

    public By getTxtPasswordLocator() {
        return this.txtPasswordLocator;
    }

    public By getTxtConfirmPasswordLocator() {
        return this.txtConfirmPasswordLocator;
    }

    public By getTxtPidLocator() {
        return this.txtPidLocator;
    }

    public By getBtnRegisterLocator() {
        return this.btnRegisterLocator;
    }

    public By getLblRegisterErrorMsgLocator() {
        return this.lblRegisterErrorMsgLocator;
    }
}


