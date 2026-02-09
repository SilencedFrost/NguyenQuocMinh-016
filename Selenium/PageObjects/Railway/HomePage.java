package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.By;

public class HomePage extends GeneralPage{

    // Locators
    private final By btnCreateAccount = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='../']/parent::li[@class='selected']"));
    }

    public HomePage open() {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }

    public RegisterPage clickCreateAccount() {
        Utilities.click(btnCreateAccount);
        return new RegisterPage();
    }

    public By getBtnCreateAccount() {
        return this.btnCreateAccount;
    }


}
