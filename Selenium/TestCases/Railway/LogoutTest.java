package TestCases.Railway;

import Common.Common.Utilities;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.FaqPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest{

    @Test
    public void TC06() {
        System.out.println("TC06 - User is redirected to Home page after logging out");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage().open();

        System.out.println("2. Login with valid Email and Password");
        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();

        System.out.println("3. Click on \"FAQ\" tab");
        FaqPage faqPage = homePage.gotoFaqPage();

        System.out.println("4. Click on \"Log out\" tab");
        faqPage.clickLogout();

        Assert.assertFalse(Utilities.isElementPresent(faqPage.getTabLogoutLocator()));
    }
}
