package TestCases.Railway;

import DataObjects.Railway.UserAccount;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest{

    @Test
    public void TC07() {
        System.out.println("TC07 - User can't create account with an already in-use email");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage().open();

        System.out.println("2. Click on \"Register\" tab");
        RegisterPage registerPage = homePage.gotoRegisterPage();

        System.out.println("3. Enter information of the created account in Pre-condition");
        System.out.println("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        String actualMsg = registerPage.getRegisterErrorMsg();
        String expectedMsg = "This email address is already in use.";

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC08() {
        System.out.println("User can't create account while password and PID fields are empty");

        // Data
        UserAccount userAccount = new UserAccount();

        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage().open();

        System.out.println("2. Click on \"Register\" tab");
        RegisterPage registerPage = homePage.gotoRegisterPage();

        System.out.println("3. Enter valid email address and leave other fields empty");
        System.out.println("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), "", "");

        String actualRegisterErrorMsg = registerPage.getRegisterErrorMsg();
        String expectedRegisterErrorMsg = "There're errors in the form. Please correct the errors and try again.";

        String actualPasswordErrorMsg = registerPage.getPasswordErrorMsg();
        String expectedPasswordErrorMsg = "Invalid password length";

        String actualPidErrorMsg = registerPage.getPidErrorMsg();
        String expectedPidErrorMsg = "Invalid ID length";

        Assert.assertEquals(actualRegisterErrorMsg.trim(), expectedRegisterErrorMsg.trim(), "Register error message is not displayed as expected");
        Assert.assertEquals(actualPasswordErrorMsg.trim(), expectedPasswordErrorMsg.trim(), "Password error message is not displayed as expected");
        Assert.assertEquals(actualPidErrorMsg.trim(), expectedPidErrorMsg.trim(), "Pid error message is not displayed as expected");
    }
}
