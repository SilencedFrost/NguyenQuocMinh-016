package TestCases.AutomationExercise;

import Common.Constant.AutomationExercise.MenuItem;
import DataObjects.AutomationExercise.UserAccount;
import PageObjects.AutomationExercise.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutTest extends BaseTest{

    @Test
    public void TC_02() {
        log.info("Verify delivery & billing addresses match registration details");

        // Data
        UserAccount userAccount = new UserAccount("name_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "@test.com");

        log.info("1 Navigate to the URL");
        HomePage homePage = new HomePage().open();

        log.info("2 Click Signup / Login");
        SignupLoginPage signupLoginPage = (SignupLoginPage) homePage.gotoPage(MenuItem.SIGNUP_LOGIN);

        log.info("3 Create a new account with random email (name_<timestamp>@test.com)");
        log.info("4 Click Continue");
        homePage = signupLoginPage.createAccount(userAccount);

        log.info("5 Add a random product to the cart");
        ProductPage productPage = ((ProductPage) homePage.gotoPage(MenuItem.PRODUCTS)).addRandomProductToCart();

        log.info("6 In the confirmation popup, click View Cart");
        CartPage cartPage = productPage.viewCart();

        log.info("7 Click Proceed to Checkout");
        CheckoutPage checkoutPage = cartPage.gotoCheckout();

        log.info("8 Verify that the Delivery Address matches the address entered during registration");
        Assert.assertEquals(checkoutPage.getAddress(), userAccount.getAddress(), "Address is not displayed as expected");
        Assert.assertEquals(checkoutPage.getCity(), userAccount.getCity(), "City is not displayed as expected");
        Assert.assertEquals(checkoutPage.getState(), userAccount.getState(), "State is not displayed as expected");
        Assert.assertEquals(checkoutPage.getZipcode(), userAccount.getZipcode(), "Zipcode is not displayed as expected");
        Assert.assertEquals(checkoutPage.getCountry(), userAccount.getCountry(), "Country is not displayed as expected");

    }
}
