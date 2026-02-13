package PageObjects.AutomationExercise;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import Common.Constant.AutomationExercise.RegisterTxt;
import DataObjects.AutomationExercise.UserAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignupLoginPage extends GeneralPage{

    // Locators
    private static final String txtByLabelXpath = "//label[normalize-space(text())='%s']/following-sibling::input";
    private static final String txtByPlaceholderXPath = "//form[@action='/signup']//input[normalize-space(@placeholder)='%s']";

    private final By btnCreateAccount = By.xpath("//button[@data-qa='create-account']");
    private final By btnSignup = By.xpath("//button[@data-qa='signup-button']");
    private final By btnContinue = By.xpath("//a[@data-qa='continue-button']");

    // Methods
    protected static WebElement findTxtByLabel(RegisterTxt registerTxt) {
        return Utilities.findElement(By.xpath(String.format(txtByLabelXpath, registerTxt.getText())));
    }

    protected static WebElement findTxtByPlaceholder(RegisterTxt registerTxt) {
        return Utilities.findElement(By.xpath(String.format(txtByPlaceholderXPath, registerTxt.getText())));
    }

    public HomePage createAccount(String name, String email, String password, String firstName, String lastName, String address, String state, String city, String zipcode, String mobileNumber) {
        findTxtByPlaceholder(RegisterTxt.NAME).sendKeys(name);
        findTxtByPlaceholder(RegisterTxt.EMAIL).sendKeys(email);

        WaitUtils.waitForElementStale(Utilities.click(btnSignup));

        findTxtByLabel(RegisterTxt.PASSWORD).sendKeys(password);
        findTxtByLabel(RegisterTxt.FIRST_NAME).sendKeys(firstName);
        findTxtByLabel(RegisterTxt.LAST_NAME).sendKeys(lastName);
        findTxtByLabel(RegisterTxt.ADDRESS).sendKeys(address);
        findTxtByLabel(RegisterTxt.STATE).sendKeys(state);
        findTxtByLabel(RegisterTxt.CITY).sendKeys(city);
        findTxtByLabel(RegisterTxt.ZIPCODE).sendKeys(zipcode);
        findTxtByLabel(RegisterTxt.MOBILE_NUMBER).sendKeys(mobileNumber);

        WaitUtils.waitForElementStale(Utilities.click(btnCreateAccount));

        WebElement continueButton = Utilities.click(btnContinue);
        if(!Utilities.isElementStale(continueButton)) {
            Utilities.disableGoogleAd();
            WaitUtils.waitForElementStale(continueButton);
        }
        return new HomePage();
    }

    public HomePage createAccount(UserAccount userAccount) {
        return this.createAccount(userAccount.getName(), userAccount.getEmail(), userAccount.getPassword(), userAccount.getFirstName(), userAccount.getLastName(), userAccount.getAddress(), userAccount.getState(), userAccount.getCity(), userAccount. getZipcode(), userAccount.getMobileNumber());
    }
}
