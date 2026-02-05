package PageObjects.Railway;

import Common.Common.Utilities;
import org.openqa.selenium.By;

public class FaqPage extends GeneralPage{

    // Locators

    // Elements

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']/parent::li[@class='selected']"));
    }
}
