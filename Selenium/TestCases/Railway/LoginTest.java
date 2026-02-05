package TestCases.Railway;

import Common.Common.RandomUtils;
import Common.Common.WaitUtils;
import Common.Constant.MenuItem;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import PageObjects.Railway.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg = "Welcome " + userAccount.getEmail();

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        System.out.println("3. Enter valid Email and Password");
        System.out.println("4. Click on \"Login\" button");
        HomePage homePage = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();
        WaitUtils.waitForElementVisibility(homePage.getLblWelcomeMessageLocator());

        System.out.println("User is logged into Railway. Welcome user message is displayed.");
        String actualMsg = homePage.getWelcomeMessage();

        // Assertion
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot log in with blank \"Username\" textbox");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox");
        System.out.println("4. Click on \"Login\" button");

        System.out.println("User can't login and message \"There was a problem with your login and/or errors exist in your form.\" appears.");
        String actualMsg = loginPage.login("", userAccount.getPassword()).expectFailure().getLoginErrorMessage();

        // Assertion
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        System.out.println("3. Enter valid Email and invalid Password");
        System.out.println("4. Click on \"Login\" button");

        System.out.println("Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
        String actualMsg = loginPage.login(userAccount.getEmail(), RandomUtils.generateRandomPassword()).expectFailure().getLoginErrorMessage();

        // Assertion
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - System shows message when user enters wrong password many times");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedLoginMsg =  "Invalid username or password. Please try again.";
        String expectedAttemptWarningMsg =  "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        // Loop 4 times
        for(int i = 1; i <= 4; i++) {
            System.out.printf("Attempt number %d\n", i);

            System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
            System.out.println("4. Click on \"Login\" button");
            System.out.println("\"Invalid username or password. Please try again\" is shown");
            String actualLoginMsg = loginPage.login(userAccount.getEmail(), RandomUtils.generateRandomPassword()).expectFailure().getLoginErrorMessage();

            // Assertion
            Assert.assertEquals(actualLoginMsg.trim(), expectedLoginMsg.trim(), "Error message is not displayed as expected");
        }

        System.out.println("User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
        String actualAttemptWarningMsg = loginPage.getLoginErrorMessage();

        // Assertion
        Assert.assertEquals(actualAttemptWarningMsg.trim(), expectedAttemptWarningMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - User can't login with an account hasn't been activated");

        // Data
        UserAccount userAccount = new UserAccount(RandomUtils.generateRandomEmail(), RandomUtils.generateRandomPassword(), RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 10));
        String expectedMsg = "Invalid username or password. Please try again.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("Setting up preconditions");
        RegisterPage registerPage = ((RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER)).register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) registerPage.gotoPage(MenuItem.LOGIN);

        System.out.println("3. Enter username and password of account hasn't been activated.");
        System.out.println("4. Click on \"Login\" button");

        System.out.println("User can't login and message \"Invalid username or password. Please try again.\" appears.");
        String actualMsg = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).getLoginErrorMessage();

        // Assertion
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
}
