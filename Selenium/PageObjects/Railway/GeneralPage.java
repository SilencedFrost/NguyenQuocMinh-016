package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Railway.MenuItem;
import org.openqa.selenium.By;

public abstract class GeneralPage {

    // Locators
    private static final String genericTabString = "//div[@id='menu']//a[@href='%s']";
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");

    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

    // Methods
    abstract boolean isPageShown();

    public String getWelcomeMessage() {
        return Utilities.findElement(lblWelcomeMessage).getText();
    }

    public GeneralPage gotoPage(MenuItem menuItem) {
        Utilities.click(By.xpath(String.format(genericTabString, menuItem.getHref())));
        return switch (menuItem) {
            case HOME -> new HomePage();
            case FAQ -> new FaqPage();
            case TIMETABLE -> new TimetablePage();
            case TICKET_PRICE -> new TicketPricePage();
            case BOOK_TICKET -> new BookTicketPage();
            case MY_TICKET -> new MyTicketPage();
            case REGISTER -> new RegisterPage();
            case LOGIN -> new LoginPage();
        };
    }

    public HomePage clickLogout() {
        Utilities.click(tabLogout);
        return new HomePage();
    }

    public By getTabLogout() {
        return this.tabLogout;
    }
}
