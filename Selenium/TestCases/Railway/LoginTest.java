package TestCases.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;

import org.openqa.selenium.WebElement;
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

        WebElement lblWelcomeMessage = loginPage.login(Constant.USERNAME, Constant.PASSWORD).expectSuccess().getLblWelcomeMessage();
        Utilities.waitForElementVisibility(Constant.WEBDRIVER, lblWelcomeMessage);
        String actualMsg = lblWelcomeMessage.getText();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot log in with blank \"Username\" textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login("", Constant.PASSWORD).expectFailure().getLblLoginErrorMsg().getText();
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
        String actualMsg = loginPage.login(Constant.USERNAME, Utilities.generateRandomString(10)).expectFailure().getLblLoginErrorMsg().getText();
        String expectedMsg =  "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
}
