package TestCases.Railway;

import Common.Common.Utilities;
import Common.Constant.MenuItem;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.FaqPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest{

    @Test
    public void TC06() {
        log.info("TC06 - User is redirected to Home page after logging out");

        // Data
        UserAccount userAccount = new UserAccount();

        // Actions
        log.info("1. Navigate to QA Railway Website");
        log.info("2. Login with valid Email and Password");
        HomePage homePage = ((LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();

        log.info("3. Click on \"FAQ\" tab");
        FaqPage faqPage = (FaqPage) homePage.gotoPage(MenuItem.FAQ);

        log.info("4. Click on \"Log out\" tab");
        homePage = faqPage.clickLogout();

        // Assertion
        log.info("Home page displays. \"Log out\" tab is disappeared.");
        Assert.assertTrue(homePage.isPageShown());
        Assert.assertFalse(Utilities.isElementPresent(faqPage.getTabLogoutLocator()));
    }
}
