package PageObjects.GuerrilaMail;

import Common.Common.Utilities;
import Common.Constant.Constant;
import org.openqa.selenium.By;

import java.time.Duration;

public class InboxPage {
    // Locators
    private final By btnInboxId = By.xpath("//span[@id='inbox-id']");
    private final By btnSetInboxId = By.xpath("//span[@id='inbox-id']/button[contains(@class, 'save')]");
    private final String btnMailTitleXpath = "//tbody[@id='email_list']//td[contains(.,'%s')]";
    private final String btnMailLinkXpath = "//a[contains(@href, '%s')]";

    private final By txtInboxId = By.xpath("//span[@id='inbox-id']/input");

    // Elements

    // Methods
    public InboxPage open() {
        Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
        return this;
    }

    public InboxPage openMailTitle(String title) {
        By btnMailTitleLocator = By.xpath(String.format(btnMailTitleXpath, title));
        Utilities.click(btnMailTitleLocator, Duration.ofSeconds(20));
        return this;
    }

    public void clickLinkContains(String link) {
        By btnMailLink = By.xpath(String.format(btnMailLinkXpath, link));
        Utilities.click(btnMailLink);
    }

    public InboxPage setMailUsername(String username) {
        Utilities.click(btnInboxId);

        Utilities.findElement(txtInboxId).sendKeys(username);

        Utilities.click(btnSetInboxId);

        return this;
    }
}
