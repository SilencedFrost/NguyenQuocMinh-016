package TestCases.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    UserAccount userAccount = new UserAccount();

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage().open();

        LoginPage loginPage = homePage.gotoLoginPage();

        homePage = loginPage.login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();
        Utilities.waitForElementVisibility(Constant.WEBDRIVER, homePage.get_lblWelcomeMessage());
        String actualMsg = homePage.getWelcomeMessage();
        String expectedMsg = "Welcome " + userAccount.getEmail();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot log in with blank \"Username\" textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login("", userAccount.getPassword()).expectFailure().getLblLoginErrorMsg().getText();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        //Random password with length of 10
        String actualMsg = loginPage.login(userAccount.getEmail(), Utilities.generateRandomString(10)).expectFailure().getLblLoginErrorMsg().getText();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - System shows message when user enters wrong password many times");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for(int i = 1; i <= 3; i++) {
            //Random password with length of 10
            String actualMsg = loginPage.login(userAccount.getEmail(), Utilities.generateRandomString(10)).expectFailure().getLblLoginErrorMsg().getText();
            String expectedMsg =  "Invalid username or password. Please try again.";

            Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
        }

        // How do I verify that user can't login? via WebElement properties such as enabled? - Minh

        String actualMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg =  "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
}
