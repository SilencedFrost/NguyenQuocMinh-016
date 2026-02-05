package TestCases.Railway;

import Common.Common.Utilities;
import Common.Constant.MenuItem;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.FaqPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class LogoutTest extends BaseTest{

    @Test
    public void TC06() {
        System.out.println("TC06 - User is redirected to Home page after logging out");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with valid Email and Password");
        HomePage homePage = ((LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();

        System.out.println("3. Click on \"FAQ\" tab");
        FaqPage faqPage = (FaqPage) homePage.gotoPage(MenuItem.FAQ);

        System.out.println("4. Click on \"Log out\" tab");
        faqPage.clickLogout();

        Assert.assertFalse(Utilities.isElementPresent(faqPage.getTabLogoutLocator()));
    }
}
