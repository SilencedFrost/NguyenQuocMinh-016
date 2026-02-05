package PageObjects.GuerrilaMail;

import Common.Common.Utilities;
import Common.Constant.Constant;
import jdk.jshell.execution.Util;
import org.openqa.selenium.By;

import java.time.Duration;

public class InboxPage {
    // Locators
    private final By btnInboxIdLocator = By.xpath("//span[@id='inbox-id']");
    private final By btnSetInboxIdLocator = By.xpath("//span[@id='inbox-id']/button[contains(@class, 'save')]");
    private final String btnMailTitleLocatorString = "//tbody[@id='email_list']//td[contains(. ,'%s')]";

    private final By txtInboxIdLocator = By.xpath("//span[@id='inbox-id']/input");

    // Elements

    // Methods
    public InboxPage open() {
        Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
        return this;
    }

    public InboxPage openMailTitle(String title) {
        By btnMailTitleLocator = By.xpath(String.format(btnMailTitleLocatorString, title));
        Utilities.click(btnMailTitleLocator, Duration.ofSeconds(15));
        return this;
    }

    public InboxPage setMailUsername(String username) {
        Utilities.click(btnInboxIdLocator);

        Utilities.findElement(txtInboxIdLocator).sendKeys(username);

        Utilities.click(btnSetInboxIdLocator);

        return this;
    }
}
