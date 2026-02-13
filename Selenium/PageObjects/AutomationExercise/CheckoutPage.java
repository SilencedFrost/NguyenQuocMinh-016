package PageObjects.AutomationExercise;

import Common.Common.Utilities;
import org.openqa.selenium.By;

import java.util.List;

public class CheckoutPage extends GeneralPage {

    // Locators
    private final By lblAddress = By.xpath("//ul[@id='address_delivery']/li[contains(@class, 'address_address1') and .!= '']");
    private final By lblCityStateZipcode = By.xpath("//ul[@id='address_delivery']/li[@class='address_city address_state_name address_postcode']");
    private final By lblCountry = By.xpath("//ul[@id='address_delivery']/li[@class='address_country_name']");

    // Methods
    public String getAddress() {
        return Utilities.findElement(lblAddress).getText();
    }

    public String getCity() {
        String[] splitString = Utilities.findElement(lblCityStateZipcode).getText().split(" ");
        return splitString[0];
    }

    public String getState() {
        String[] splitString = Utilities.findElement(lblCityStateZipcode).getText().split(" ");
        return splitString[1];
    }

    public String getZipcode() {
        String[] splitString = Utilities.findElement(lblCityStateZipcode).getText().split(" ");
        return splitString[2];
    }

    public String getCountry() {
        return Utilities.findElement(lblCountry).getText();
    }
    
}
