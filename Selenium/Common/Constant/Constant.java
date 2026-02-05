package Common.Constant;


import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Constant {

    public static WebDriver WEBDRIVER;
    public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
    public static final Duration TIMEOUT = Duration.ofSeconds(10);
}