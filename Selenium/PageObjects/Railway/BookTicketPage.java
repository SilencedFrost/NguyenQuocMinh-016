package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import Common.Constant.Constant;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.SeatType;
import Common.Constant.Railway.TicketHeader;
import DataObjects.Railway.TicketInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class BookTicketPage extends GeneralPage{

    // Locators
    private final By cboDepartDate = By.xpath("//select[@name='Date']");
    private final By cboDepartLocation = By.xpath("//select[@name='DepartStation']");
    private final By cboArriveLocation = By.xpath("//select[@name='ArriveStation']");
    private final By cboSeatType = By.xpath("//select[@name='SeatType']");
    private final By cboTicketAmount = By.xpath("//select[@name='TicketAmount']");

    private final By btnSubmitLocator = By.xpath("//form//input[@type='submit']");

    private final By lblTitleLocator = By.xpath("//div[@id='content']/h1");

    private static final String tblCellByHeaderLocatorString = "//tr/td[position() = count(//th[.='%s']/preceding-sibling::th) + 1]";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']/parent::li[@class='selected']"));
    }

    public LocalDate getDepartDate() {
        return LocalDate.parse(new Select(Utilities.findElement(cboDepartDate)).getFirstSelectedOption().getText(), Constant.DATE_FORMAT);
    }

    public String getDepartLocation() {
        return new Select(Utilities.findElement(cboDepartLocation)).getFirstSelectedOption().getText();
    }

    public String getArriveLocation() {
        return new Select(Utilities.findElement(cboArriveLocation)).getFirstSelectedOption().getText();
    }

    public String getSeatType() {
        return new Select(Utilities.findElement(cboSeatType)).getFirstSelectedOption().getText();
    }

    public String getTitle() {
        return Utilities.findElement(lblTitleLocator).getText();
    }

    public String getCellValue(TicketHeader header) {
        return Utilities.findElement(By.xpath(String.format(tblCellByHeaderLocatorString, header.getText()))).getText();
    }

    public BookTicketPage selectDepartDate(LocalDate date) {
        Utilities.selectComboboxByVisibleText(cboDepartDate, date.format(Constant.DATE_FORMAT));
        return this;
    }

    public BookTicketPage selectDepartLocation(Location location) {
        Utilities.selectComboboxByVisibleText(cboDepartLocation, location.getText());
        return this;
    }

    public BookTicketPage selectArriveLocation(Location location) {
        Utilities.selectComboboxByVisibleText(cboArriveLocation, location.getText());
        return this;
    }

    public BookTicketPage selectSeatType(SeatType seatType) {
        Utilities.selectComboboxByVisibleText(cboSeatType, seatType.getText());
        return this;
    }

    // amount 1-10, if outside this range, default will be 1
    public BookTicketPage selectTicketAmount(Integer amount) {
        if(amount < 1 || amount > 10) {
            amount = 1;
        }
        Utilities.selectComboboxByVisibleText(cboTicketAmount, amount.toString());
        return this;
    }

    // Setting any params as NULL to leave the field as form default
    public BookTicketPage bookTicket(LocalDate departDate, Location departLocation, Location arriveLocation, SeatType seatType, Integer amount) {
        if(departDate != null) selectDepartDate(departDate);
        if(departLocation != null){
            String currentDepartLocation = this.getDepartLocation();
            if(!currentDepartLocation.equals(departLocation.getText())) {
                WebElement cboArriveLocation = Utilities.findElement(this.cboArriveLocation);
                selectDepartLocation(departLocation);
                WaitUtils.waitForElementStale(cboArriveLocation);
            }
        }
        if(arriveLocation != null) selectArriveLocation(arriveLocation);
        if(seatType != null) selectSeatType(seatType);
        if(amount != null) selectTicketAmount(amount);
        Utilities.click(btnSubmitLocator);
        return this;
    }

    public BookTicketPage bookTicket(TicketInformation ticketInformation) {
        return this.bookTicket(ticketInformation.getDepartDate(), ticketInformation.getDepartLocation(), ticketInformation.getArriveLocation(), ticketInformation.getSeatType(), ticketInformation.getTicketAmount());
    }
}
