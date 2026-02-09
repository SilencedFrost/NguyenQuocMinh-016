package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Common.WaitUtils;
import Common.Constant.Constant;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.TimetableHeader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class MyTicketPage extends GeneralPage{

    // Locators
    private static final String tblMyTicketXpath = "//div[@class='DivTable']/table";
    private static final String rowXpath = "//tr";
    private static final String tblRowSelectorByHeaderAndValueOptionXpath = "[./td[position() = count(//th[.='%s']/preceding-sibling::th) + 1 and .='%s']]";
    private static final String btnDeleteXpath = "//input[@type='button' and @value='Cancel']";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']/parent::li[@class='selected']"));
    }

    // Build an XPath selector for table rows matching the specified departure and arrival locations
    private String buildRowXpathForDepartureAndArrival(Location departLocation, Location arriveLocation) {
        // XPath predicate (selector) for value from depart location column
        String departLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueOptionXpath, TimetableHeader.DEPART_STATION.getText(), departLocation.getText());
        // XPath predicate (selector) for value from arrival location column
        String arriveLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueOptionXpath, TimetableHeader.ARRIVE_STATION.getText(), arriveLocation.getText());

        return tblMyTicketXpath + rowXpath + departLocationXpathFragment + arriveLocationXpathFragment;
    }

    public MyTicketPage deleteTicketForRoute(Location departLocation, Location arriveLocation) {
        String fullXpath = buildRowXpathForDepartureAndArrival(departLocation, arriveLocation) + btnDeleteXpath;

        // Click delete
        Utilities.click(By.xpath(fullXpath));

        // Process alert
        WaitUtils.waitForAlertPresent();
        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();

        return this;
    }

    public boolean isRowForRoutePresent(Location departLocation, Location arriveLocation) {
        String fullXpath = buildRowXpathForDepartureAndArrival(departLocation, arriveLocation);

        return Utilities.isElementPresent(By.xpath(fullXpath));
    }
}
