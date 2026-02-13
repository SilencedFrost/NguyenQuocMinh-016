package PageObjects.AutomationExercise;

import Common.Common.Utilities;
import DataObjects.AutomationExercise.UserAccount;
import org.openqa.selenium.By;

public class ProductDetailPage extends GeneralPage{

    // Locators
    private final By btnWriteReview = By.xpath("//a[@href='#reviews']");
    private final By btnSubmitReview = By.xpath("//form[@id='review-form']//button[@id='button-review']");

    private final By txtReviewUsername = By.xpath("//form[@id='review-form']//input[@id='name']");
    private final By txtReviewEmail = By.xpath("//form[@id='review-form']//input[@id='email']");
    private final By txtReviewText = By.xpath("//form[@id='review-form']//textarea[@id='review']");

    private final By lblReviewSuccessAlert = By.xpath("//div[@class='alert-success alert']/span");

    // Methods
    public String getReviewSuccessAlert() {
        return Utilities.findElement(lblReviewSuccessAlert).getText();
    }

    public ProductDetailPage review(String username, String email, String commentContent) {
        Utilities.findElement(txtReviewUsername).sendKeys(username);
        Utilities.findElement(txtReviewEmail).sendKeys(email);
        Utilities.findElement(txtReviewText).sendKeys(commentContent);

        Utilities.click(btnSubmitReview);

        return this;
    }

    public ProductDetailPage review(UserAccount userAccount, String commentContent) {
        return this.review(userAccount.getName(), userAccount.getEmail(), commentContent);
    }

    public By getBtnWriteReview() {
        return this.btnWriteReview;
    }
}
