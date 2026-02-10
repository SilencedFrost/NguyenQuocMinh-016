package TestCases.Railway;

import Common.Common.Utilities;
import Common.Common.WindowUtils;
import Common.Constant.Constant;
import Common.Constant.EmailDomain;
import Common.Constant.Railway.MailTitle;
import Common.Constant.Railway.MenuItem;
import DataObjects.Railway.UserAccount;
import PageObjects.GuerrilaMail.InboxPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.RegisterPage;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest{

    @Test
    public void TC07() {
        log.info("TC07 - User can't create account with an already in-use email");

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedMsg = "This email address is already in use.";

        // Actions
        log.info("1. Navigate to QA Railway Website");
        log.info("2. Click on \"Register\" tab");
        RegisterPage registerPage = (RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER);

        log.info("3. Enter information of the created account in Pre-condition");
        log.info("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        log.info("Error message \"This email address is already in use.\" displays above the form.");
        String actualMsg = registerPage.getRegisterErrorMsg();

        // Assertion
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }

    @Test
    public void TC08() {
        log.info("User can't create account while password and PID fields are empty");
        String expectedPasswordErrorMsg = "Invalid password length";
        String expectedPidErrorMsg = "Invalid ID length";

        // Data
        UserAccount userAccount = new UserAccount();
        String expectedRegisterErrorMsg = "There're errors in the form. Please correct the errors and try again.";

        // Actions
        log.info("1. Navigate to QA Railway Website");
        log.info("2. Click on \"Register\" tab");
        RegisterPage registerPage = (RegisterPage) new HomePage().open().gotoPage(MenuItem.REGISTER);

        log.info("3. Enter valid email address and leave other fields empty");
        log.info("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), "", "");

        log.info("""
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
        log.info("TC09 - User create and activate account");

        // Data
        UserAccount userAccount = new UserAccount().getRandomUser(EmailDomain.GUERRILLA);
        String expectedRegisterMsg = "Thank you for registering your account";
        String expectedConfirmationMsg = "Registration Confirmed! You can now log in to the site.";

        // Actions
        log.info("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage().open();

        // Assertion
        log.info("Home page is shown with guide containing href \"create an account\" to \"Register\" page");
        Assert.assertTrue(homePage.isPageShown());
        Assert.assertTrue(Utilities.isElementPresent(homePage.getBtnCreateAccount()));

        // Actions
        log.info("2. Click on \"Create an account\"");
        RegisterPage registerPage = homePage.clickCreateAccount();

        // Assertion
        log.info("Register page is shown");
        Assert.assertTrue(registerPage.isPageShown());

        // Actions
        log.info("3. Enter valid information into all fields");
        log.info("4. Click on \"Register\" button");
        registerPage.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        log.info("\"Thank you for registering your account\" is shown");
        String actualRegisterMsg = registerPage.getTitle();

        // Assertion
        Assert.assertEquals(actualRegisterMsg.trim(), expectedRegisterMsg.trim(), "Register message is not displayed as expected");

        // Actions
        log.info("5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        WindowUtils.closeFirstWindow();
        InboxPage guerrillaInboxPage = new InboxPage().open();

        log.info("6. Login to the mailbox");
        guerrillaInboxPage.setMailUsername(userAccount.getUsername());

        log.info("7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
        guerrillaInboxPage.openMailTitle(MailTitle.CONFIRM_ACCOUNT);

        log.info("8. Click on the activate link");
        guerrillaInboxPage.clickLinkContains(Constant.RAILWAY_CONFIRM_REGISTRATION_URL);
        WindowUtils.switchToLatestWindow();
        WindowUtils.closeFirstWindow();

        // Assertion
        log.info("Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
        String actualConfirmationMsg = registerPage.getConfirmMsg();

        Assert.assertEquals(actualConfirmationMsg.trim(), expectedConfirmationMsg.trim(), "Confirmation message is not displayed as expected");
    }
}
