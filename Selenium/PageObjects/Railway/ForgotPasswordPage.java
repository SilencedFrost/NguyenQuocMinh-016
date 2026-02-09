package PageObjects.Railway;

import Common.Common.Utilities;
import org.openqa.selenium.By;

public class ForgotPasswordPage extends GeneralPage{

    // Locators
    private final By txtEmail = By.xpath("//form//input[@id='email']");

    private final By btnSubmit = By.xpath("//form//input[@type='submit']");

    // Elements

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//form//legend[contains(., 'Password Reset')]"));
    }

    public ForgotPasswordPage submitRequest(String email) {
        Utilities.findElement(txtEmail).sendKeys(email);

        Utilities.click(btnSubmit);

        return this;
    }
}
