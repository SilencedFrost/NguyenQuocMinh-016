package PageObjects.Railway;

import Common.Common.Utilities;
import org.openqa.selenium.By;

public class ChangePasswordPage extends GeneralPage{

    // Locators
    private final By txtPassword = By.xpath("//form//input[@id='newPassword']");
    private final By txtConfirmPassword = By.xpath("//form//input[@id='confirmPassword']");
    private final By txtResetToken = By.xpath("//form//input[@id='resetToken']");

    private final By btnSubmit = By.xpath("//form//input[@type='submit']");

    private final By lblSubmitMessage = By.xpath("//div[@id='content']/p[contains(@class, 'message')]");
    private final By lblConfirmPasswordMessage = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//form//legend[contains(., 'Password Change')]"));
    }

    public String getResetToken() {
        return Utilities.findElement(txtResetToken).getAttribute("value");
    }

    public String getSubmitMessage() {
        return Utilities.findElement(lblSubmitMessage).getText();
    }

    public String getConfirmPasswordMessage() {
        return Utilities.findElement(lblConfirmPasswordMessage).getText();
    }

    public ChangePasswordPage changePassword(String password, String confirmPassword) {
        Utilities.findElement(txtConfirmPassword).sendKeys(password);
        Utilities.findElement(txtPassword).sendKeys(confirmPassword);

        Utilities.click(btnSubmit);

        return this;
    }

    public ChangePasswordPage changePassword(String password) {
        return changePassword(password, password);
    }
}
