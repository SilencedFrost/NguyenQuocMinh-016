package TestCases.Railway;

import Common.Common.Random;
import Common.Common.Utilities;
import Common.Constant.Constant;
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

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage().open();

        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        System.out.println("3. Enter valid Email and Password");
        System.out.println("4. Click on \"Login\" button");
        homePage = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();
        Utilities.waitForElementVisibility(Constant.WEBDRIVER, homePage.getLblWelcomeMessageLocator());
        String actualMsg = homePage.getWelcomeMessage();
        String expectedMsg = "Welcome " + userAccount.getEmail();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot log in with blank \"Username\" textbox");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox");
        System.out.println("4. Click on \"Login\" button");
        String actualMsg = loginPage.login("", userAccount.getPassword()).expectFailure().getLoginErrorMessage();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        System.out.println("3. Enter valid Email and invalid Password");
        System.out.println("4. Click on \"Login\" button");
        String actualMsg = loginPage.login(userAccount.getEmail(), Random.generateRandomPassword()).expectFailure().getLoginErrorMessage();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - System shows message when user enters wrong password many times");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        for(int i = 1; i <= 3; i++) {
            System.out.printf("Attempt number %d\n", i);

            System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
            System.out.println("4. Click on \"Login\" button");
            String actualMsg = loginPage.login(userAccount.getEmail(), Random.generateRandomPassword()).expectFailure().getLoginErrorMessage();
            String expectedMsg =  "Invalid username or password. Please try again.";

            Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
        }

        // How do I verify that user can't log in? via WebElement properties such as enabled? - Minh

        String actualMsg = loginPage.getLoginErrorMessage();
        String expectedMsg =  "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - User can't login with an account hasn't been activated");
        System.out.println("Setting up preconditions");

        // Data
        UserAccount userAccount = new UserAccount(Random.generateRandomEmail(), Random.generateRandomPassword(), Random.generateRandomString(Random.NUMERICAL, 10));

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = registerPage.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid()).gotoLoginPage();

        // Start test case
        System.out.println("3. Enter username and password of account hasn't been activated.");
        System.out.println("4. Click on \"Login\" button");
        String actualMsg = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).getLoginErrorMessage();
        String expectedMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
}
