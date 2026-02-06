package TestCases.Railway;

import Common.Common.RandomUtils;
import Common.Common.Utilities;
import Common.Constant.Constant;
import Common.Constant.EmailDomains;
import Common.Constant.MenuItem;
import DataObjects.Railway.UserAccount;
import PageObjects.GuerrilaMail.InboxPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.RegisterPage;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class RegisterTest extends BaseTest{

    @Test
    public void TC07() {
        System.out.println("TC07 - User can't create account with an already in-use email");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg = "This email address is already in use.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Register\" tab");
        RegisterPage registerPage = (RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER);

        System.out.println("3. Enter information of the created account in Pre-condition");
        System.out.println("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        System.out.println("Error message \"This email address is already in use.\" displays above the form.");
        String actualMsg = registerPage.getRegisterErrorMsg();

        // Assertion
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC08() {
        System.out.println("User can't create account while password and PID fields are empty");
        String expectedPasswordErrorMsg = "Invalid password length";
        String expectedPidErrorMsg = "Invalid ID length";

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedRegisterErrorMsg = "There're errors in the form. Please correct the errors and try again.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Register\" tab");
        RegisterPage registerPage = (RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER);

        System.out.println("3. Enter valid email address and leave other fields empty");
        System.out.println("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), "", "");

        System.out.println("""
                        Message "There're errors in the form. Please correct the errors and try again." appears above the form.
                        Next to password fields, error message "Invalid password length." displays
                        Next to PID field, error message "Invalid ID length." displays"""
        );
        String actualRegisterErrorMsg = registerPage.getRegisterErrorMsg();
        String actualPasswordErrorMsg = registerPage.getPasswordErrorMsg();
        String actualPidErrorMsg = registerPage.getPidErrorMsg();

        // Assertions
        Assert.assertEquals(actualRegisterErrorMsg.trim(), expectedRegisterErrorMsg.trim(), "Register error message is not displayed as expected");
        Assert.assertEquals(actualPasswordErrorMsg.trim(), expectedPasswordErrorMsg.trim(), "Password error message is not displayed as expected");
        Assert.assertEquals(actualPidErrorMsg.trim(), expectedPidErrorMsg.trim(), "Pid error message is not displayed as expected");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User create and activate account");

        // Data
        UserAccount userAccount = new UserAccount(
                // Random guerrilla email address
                RandomUtils.generateRandomString(15),
                EmailDomains.GUERRILLA,
                // Random password
                RandomUtils.generateRandomPassword(),
                // Random Pid
                RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 12));
        String expectedRegisterMsg = "Thank you for registering your account";
        String expectedConfirmationMsg = "Registration Confirmed! You can now log in to the site.";

        // Actions
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage().open();

        // Assertion
        System.out.println("Home page is shown with guide containing href \"create an account\" to \"Register\" page");
        Assert.assertTrue(homePage.isPageShown());
        Assert.assertTrue(Utilities.isElementPresent(homePage.getBtnCreateAccountLocator()));

        // Actions
        System.out.println("2. Click on \"Create an account\"");

        RegisterPage registerPage = homePage.clickCreateAccount();

        // Assertion
        System.out.println("Register page is shown");
        Assert.assertTrue(registerPage.isPageShown());

        // Actions
        System.out.println("3. Enter valid information into all fields");
        System.out.println("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        System.out.println("\"Thank you for registering your account\" is shown");
        String actualRegisterMsg = registerPage.getTitle();

        // Assertion
        Assert.assertEquals(actualRegisterMsg.trim(), expectedRegisterMsg.trim(), "Register message is not displayed as expected");

        // Actions
        System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        InboxPage guerrillaInboxPage = new InboxPage().open();

        System.out.println("6. Login to the mailbox");
        guerrillaInboxPage.setMailUsername(userAccount.getUsername());

        System.out.println("7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
        guerrillaInboxPage.openMailTitle("Please confirm your account");

        System.out.println("8. Click on the activate link");
        guerrillaInboxPage.clickLinkContains(Constant.RAILWAY_CONFIRM_URL);
        Utilities.switchToLatestWindow();

        System.out.println("Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
        String actualConfirmationMsg = registerPage.getConfirmMsg();

        //Assertion
        Assert.assertEquals(actualConfirmationMsg.trim(), expectedConfirmationMsg.trim(), "Confirmation message is not displayed as expected");
    }
}
