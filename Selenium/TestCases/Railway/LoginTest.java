package TestCases.Railway;

import Common.Common.Random;
import Common.Common.Utilities;
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

        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        System.out.println("3. Enter valid Email and Password");
        System.out.println("4. Click on \"Login\" button");
        HomePage homePage = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();
        Utilities.waitForElementVisibility(homePage.getLblWelcomeMessageLocator());

        String actualMsg = homePage.getWelcomeMessage();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot log in with blank \"Username\" textbox");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox");
        System.out.println("4. Click on \"Login\" button");

        String actualMsg = loginPage.login("", userAccount.getPassword()).expectFailure().getLoginErrorMessage();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        System.out.println("3. Enter valid Email and invalid Password");
        System.out.println("4. Click on \"Login\" button");

        String actualMsg = loginPage.login(userAccount.getEmail(), Random.generateRandomPassword()).expectFailure().getLoginErrorMessage();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - System shows message when user enters wrong password many times");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedLoginMsg =  "Invalid username or password. Please try again.";
        String expectedAttemptWarningMsg =  "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) new HomePage().open().gotoPage(MenuItem.LOGIN);

        // Loop 4 times
        for(int i = 1; i <= 4; i++) {
            System.out.printf("Attempt number %d\n", i);

            System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
            System.out.println("4. Click on \"Login\" button");
            String actualLoginMsg = loginPage.login(userAccount.getEmail(), Random.generateRandomPassword()).expectFailure().getLoginErrorMessage();

            Assert.assertEquals(actualLoginMsg.trim(), expectedLoginMsg.trim(), "Error message is not displayed as expected");
        }

        String actualAttemptWarningMsg = loginPage.getLoginErrorMessage();

        Assert.assertEquals(actualAttemptWarningMsg.trim(), expectedAttemptWarningMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - User can't login with an account hasn't been activated");

        // Data
        UserAccount userAccount = new UserAccount(Random.generateRandomEmail(), Random.generateRandomPassword(), Random.generateRandomString(Random.NUMERICAL, 10));
        String expectedMsg = "Invalid username or password. Please try again.";

        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("Setting up preconditions");
        RegisterPage registerPage = ((RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER)).register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        // Start test case
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = (LoginPage) registerPage.gotoPage(MenuItem.LOGIN);

        System.out.println("3. Enter username and password of account hasn't been activated.");
        System.out.println("4. Click on \"Login\" button");

        String actualMsg = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).getLoginErrorMessage();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
}
