package TestCases.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import PageObjects.Railway.GeneralPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

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

        GeneralPage generalPage = loginPage.loginSuccess(Constant.USERNAME, Constant.PASSWORD);
        Utilities.waitForElementVisibility(Constant.WEBDRIVER, generalPage.getLblWelcomeMessage());
        String actualMsg = generalPage.getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("User cannot log in with blank \"Username\" textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.loginFail("", Constant.PASSWORD).getLblLoginErrorMsg().getText();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        //Random password with length of 10
        String actualMsg = loginPage.loginFail(Constant.USERNAME, Utilities.generateRandomString(10)).getLblLoginErrorMsg().getText();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
}
