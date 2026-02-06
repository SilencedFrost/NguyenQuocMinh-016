package PageObjects.Railway;

import Common.Common.Utilities;
import org.openqa.selenium.By;

public class ChangePasswordPage extends GeneralPage{

    // Locators
    private final By txtPasswordLocator = By.xpath("//form//input[@id='newPassword']");
    private final By txtConfirmPasswordLocator = By.xpath("//form//input[@id='confirmPassword']");
    private final By txtResetTokenLocator = By.xpath("//form//input[@id='resetToken']");

    private final By btnSubmitLocator = By.xpath("//form//input[@type='submit']");

    private final By lblSubmitMessageLocator = By.xpath("//div[@id='content']/p[contains(@class, 'message')]");
    private final By lblConfirmPasswordMessageLocator = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//form//legend[contains(., 'Password Change')]"));
    }

    public String getResetToken() {
        return Utilities.findElement(txtResetTokenLocator).getAttribute("value");
    }

    public String getSubmitMessage() {
        return Utilities.findElement(lblSubmitMessageLocator).getText();
    }

    public String getConfirmPasswordMessage() {
        return Utilities.findElement(lblConfirmPasswordMessageLocator).getText();
    }

    public ChangePasswordPage changePassword(String password, String confirmPassword) {
        Utilities.findElement(txtConfirmPasswordLocator).sendKeys(password);
        Utilities.findElement(txtPasswordLocator).sendKeys(confirmPassword);

        Utilities.click(btnSubmitLocator);

        return this;
    }

    public ChangePasswordPage changePassword(String password) {
        return changePassword(password, password);
    }
}
