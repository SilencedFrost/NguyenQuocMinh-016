package BusinessFlow.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import Common.Constant.MenuItem;
import PageObjects.GuerrilaMail.InboxPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.RegisterPage;
import org.openqa.selenium.WindowType;

public class RegisterAccountFlow {

    public static RegisterPage registerAndActivate(String username, String email, String password, String pid) {
        register(email, password, pid);
        return activate(username);
    }

    public static RegisterPage register(String email, String password, String pid) {
        return ((RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER))
                .register(email, password, pid);
    }

    public static RegisterPage activate(String username) {
        // Activate
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        new InboxPage().open()
                .setMailUsername(username)
                .openMailTitle("Please confirm your account")
                .clickLinkContains(Constant.RAILWAY_CONFIRM_REGISTRATION_URL);
        // Focus on confirmation tab
        Utilities.switchToLatestWindow();
        return new RegisterPage();

    }
}
