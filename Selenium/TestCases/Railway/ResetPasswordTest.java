package TestCases.Railway;

import BusinessFlow.Railway.RegisterAccountFlow;
import Common.Common.RandomUtils;
import Common.Common.Utilities;
import Common.Constant.Constant;
import Common.Constant.EmailDomains;
import Common.Constant.MenuItem;
import DataObjects.Railway.UserAccount;
import PageObjects.GuerrilaMail.InboxPage;
import PageObjects.Railway.ChangePasswordPage;
import PageObjects.Railway.ForgotPasswordPage;
import PageObjects.Railway.LoginPage;
import PageObjects.Railway.RegisterPage;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseTest{

    @Test
    public void TC10() {
        log.info("TC10 - Reset password shows error if the new password is same as current");

        // Data
        UserAccount userAccount = new UserAccount(
                // Random guerrilla email address
                RandomUtils.generateRandomString(15),
                EmailDomains.GUERRILLA,
                // Random password
                RandomUtils.generateRandomPassword(),
                // Random Pid
                RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 12));
        String mailTitle = "Please reset your password";
        String expectedMsg = "The new password cannot be the same with the current password";

        log.info("Pre-condition: an activated account is existing");
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount.getUsername(), userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        // Actions
        log.info("1. Navigate to QA Railway Login page");
        LoginPage loginPage = (LoginPage) registerPage.gotoPage(MenuItem.LOGIN);

        log.info("2. Click on \"Forgot Password page\" link");
        ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPassword();

        log.info("3. Enter the email address of the activated account");
        log.info("4. Click on \"Send Instructions\" button");
        forgotPasswordPage.submitRequest(userAccount.getEmail());

        log.info("5. Login to the mailbox (the same mailbox when creating account)");
        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        InboxPage guerrillaInboxPage = new InboxPage().open().setMailUsername(userAccount.getUsername());

        log.info("6. Open email with subject containing \"Please reset your password\" and the email of the account at step 3");
        guerrillaInboxPage.openMailTitle(mailTitle);

        log.info("7. Click on reset link");
        guerrillaInboxPage.clickLinkContains(Constant.RAILWAY_RESET_PASSWORD_URL);
        Utilities.switchToLatestWindow();
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();

        // Assertion
        log.info("Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
        Assert.assertTrue(changePasswordPage.isPageShown());
        String resetPasswordToken = changePasswordPage.getResetToken();
        Assert.assertFalse(resetPasswordToken.isEmpty());

        // Actions
        log.info("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
        log.info("9. Click Reset Password");
        changePasswordPage.changePassword(userAccount.getPassword());

        // Assertion
        log.info("Message \"The new password cannot be the same with the current password\" is shown");
        String actualMsg = changePasswordPage.getSubmitMessage();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");
    }
}
