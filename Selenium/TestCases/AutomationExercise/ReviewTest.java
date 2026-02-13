package TestCases.AutomationExercise;

import Common.Common.RandomUtils;
import Common.Common.Utilities;
import Common.Constant.AutomationExercise.MenuItem;
import DataObjects.AutomationExercise.UserAccount;
import PageObjects.AutomationExercise.HomePage;
import PageObjects.AutomationExercise.ProductDetailPage;
import PageObjects.AutomationExercise.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReviewTest extends BaseTest{

    @Test
    public void TC_01() {
        log.info("TC_01 - Verify user can submit a product review successfully");

        //Data
        UserAccount userAccount = new UserAccount();
        String commentContent = RandomUtils.generateRandomString(RandomUtils.LOWERCASE_ALPHA, 10);
        String expectedMsg = "Thank you for your review.";

        log.info("1. Navigate to the URL");
        HomePage homePage = new HomePage().open();

        log.info("2. Click Products");
        ProductPage productPage = (ProductPage) homePage.gotoPage(MenuItem.PRODUCTS);

        log.info("3. Select any product by clicking View Product on a random item");
        ProductDetailPage productDetailPage = productPage.viewRandomProduct();

        log.info("4. Verify that the “Write Your Review” section is displayed");
        Assert.assertTrue(Utilities.isElementPresent(productDetailPage.getBtnWriteReview()));

        log.info("5. Enter valid Name, Email, and Review text");
        log.info("6. Click Submit");
        productDetailPage.review(userAccount, commentContent);

        log.info("7. Verify the success message “Thank you for your review.” is displayed.");
        String actualMsg = productDetailPage.getReviewSuccessAlert();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Review message is not displayed as expected");
    }
}
