package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import Common.Constant.Constant;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.SeatType;
import Common.Constant.Railway.TicketHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class BookTicketPage extends GeneralPage{

    // Locators
    private final By cboDepartDateLocator = By.xpath("//select[@name='Date']");
    private final By cboDepartLocationLocator = By.xpath("//select[@name='DepartStation']");
    private final By cboArriveLocationLocator = By.xpath("//select[@name='ArriveStation']");
    private final By cboSeatTypeLocator = By.xpath("//select[@name='SeatType']");
    private final By cboTicketAmountLocator = By.xpath("//select[@name='TicketAmount']");

    private final By btnSubmitLocator = By.xpath("//form//input[@type='submit']");

    private final By lblTitleLocator = By.xpath("//div[@id='content']/h1");

    private static final String tblCellByHeaderLocatorString = "//tr/td[position() = count(//th[.='%s']/preceding-sibling::th) + 1]";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']/parent::li[@class='selected']"));
    }

    public LocalDate getDepartDate() {
        return LocalDate.parse(new Select(Utilities.findElement(cboDepartDateLocator)).getFirstSelectedOption().getText(), Constant.DATE_FORMAT);
    }

    public String getTitle() {
        return Utilities.findElement(lblTitleLocator).getText();
    }

    public String getCellValue(TicketHeader header) {
        return Utilities.findElement(By.xpath(String.format(tblCellByHeaderLocatorString, header.getText()))).getText();
    }

    public BookTicketPage selectDepartDate(LocalDate date) {
        Utilities.selectComboboxByVisibleText(cboDepartDateLocator, date.format(Constant.DATE_FORMAT));
        return this;
    }

    public BookTicketPage selectDepartLocation(Location location) {
        Utilities.selectComboboxByVisibleText(cboDepartLocationLocator, location.getVisibleText());
        return this;
    }

    public BookTicketPage selectArriveLocation(Location location) {
        Utilities.selectComboboxByVisibleText(cboArriveLocationLocator, location.getVisibleText());
        return this;
    }

    public BookTicketPage selectSeatType(SeatType seatType) {
        Utilities.selectComboboxByVisibleText(cboSeatTypeLocator, seatType.getText());
        return this;
    }

    /**
     * @param amount 1-10, if outside this range, default will be 1
     * @return the page object
     */
    public BookTicketPage selectTicketAmount(Integer amount) {
        if(amount < 1 || amount > 10) {
            amount = 1;
        }
        Utilities.selectComboboxByVisibleText(cboTicketAmountLocator, amount.toString());
        return this;
    }

    public BookTicketPage bookTicket(LocalDate date, Location departLocation, Location arriveLocation, SeatType seatType, Integer amount) {
        selectDepartDate(date);
        selectDepartLocation(departLocation);
        WaitUtils.waitForElementStale(cboArriveLocationLocator);
        selectArriveLocation(arriveLocation);
        selectSeatType(seatType);
        selectTicketAmount(amount);
        Utilities.click(btnSubmitLocator);
        return this;
    }
}
