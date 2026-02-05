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

    // Elements
    protected WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmailLocator);
    }

    protected WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPasswordLocator);
    }

    protected WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(btnLoginLocator);
    }

    protected WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(lblLoginErrorMsgLocator);
    }

    // Methods
    public String getLoginErrorMessage() {
        return this.getLblLoginErrorMsg().getText();
    }

    public LoginPage login(String email, String password) {
        this.getTxtEmail().sendKeys(email);
        this.getTxtPassword().sendKeys(password);

        Utilities.waitForElementClickable(Constant.WEBDRIVER, btnLoginLocator);

        this.getBtnLogin().click();
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


