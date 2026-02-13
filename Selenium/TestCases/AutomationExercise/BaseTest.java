package TestCases.AutomationExercise;

import Common.Constant.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browser) {
        log.info("Pre-condition");

        Constant.WEBDRIVER = switch(browser) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> throw new RuntimeException("Unsupported browser: " + browser);
        };
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        log.info("Post-condition");

        Constant.WEBDRIVER.quit();
    }
}
