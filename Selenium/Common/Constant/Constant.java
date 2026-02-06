package Common.Constant;


import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Constant {

    public static WebDriver WEBDRIVER;
    public static final String RAILWAY_URL = "http://saferailway.somee.com/Page/HomePage.cshtml";
    public static final String GUERRILLA_URL = "https://www.guerrillamail.com/inbox";
    public static final String RAILWAY_CONFIRM_REGISTRATION_URL = "www.saferailway.somee.com/Account/Confirm";
    public static final String RAILWAY_RESET_PASSWORD_URL = "http://www.saferailway.somee.com/Account/PasswordReset";
    public static final Duration FIND_ELEMENT_TIMEOUT = Duration.ofSeconds(10);
    public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(60);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");
}