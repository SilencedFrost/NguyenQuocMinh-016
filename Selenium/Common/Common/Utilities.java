package Common.Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.Duration;

public class Utilities {

    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(Integer length) {
        if (length == null || length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer");
        }

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHANUMERIC.length());
            result.append(ALPHANUMERIC.charAt(index));
        }

        return result.toString();
    }

    public static String generateRandomEmail() {
        // Generate random username (8-12 characters)
        int usernameLength = 8 + random.nextInt(5); // 8 to 12
        String username = generateRandomString(usernameLength);

        // Common email domains
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};
        String domain = domains[random.nextInt(domains.length)];

        return username + "@" + domain;
    }

    public static void waitForElementVisibility(WebDriver driver, WebElement element, Duration time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisibility(WebDriver driver, WebElement element) {
        waitForElementVisibility(driver, element, Duration.ofSeconds(10));
    }
}