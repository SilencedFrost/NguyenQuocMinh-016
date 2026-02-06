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

    public ChangePasswordPage changePassword(String password) {
        Utilities.findElement(txtConfirmPasswordLocator).sendKeys(password);
        Utilities.findElement(txtPasswordLocator).sendKeys(password);

        Utilities.click(btnSubmitLocator);

        return this;
    }
}
