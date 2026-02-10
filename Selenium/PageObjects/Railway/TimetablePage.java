package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.TimetableFeature;
import Common.Constant.Railway.TimetableHeader;
import DataObjects.Railway.TicketInformation;
import DataObjects.Railway.TicketPriceInformation;
import org.openqa.selenium.By;

public class TimetablePage extends GeneralPage{

    // Locators
    private static final String tblTimetableXpath = "//div[@class='DivTable']/table";
    private static final String rowXpath = "//tr";
    private static final String tblRowSelectorByHeaderAndValueOptionXpath = "[./td[position() = count(//th[.='%s']/preceding-sibling::th) + 1 and .='%s']]";
    private static final String btnOptionXpath = "//a[contains(@href, '%s')]";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']/parent::li[@class='selected']"));
    }

    // Build an XPath selector for table rows matching the specified departure and arrival locations
    private String buildRowXpathForDepartureAndArrival(Location departLocation, Location arriveLocation) {
        // XPath predicate (selector) for value from depart location column
        String departLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueOptionXpath, TimetableHeader.DEPART_STATION.getText(), departLocation.getText());
        // XPath predicate (selector) for value from arrival location column
        String arriveLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueOptionXpath, TimetableHeader.ARRIVE_STATION.getText(), arriveLocation.getText());

        return tblTimetableXpath + rowXpath + departLocationXpathFragment + arriveLocationXpathFragment;
    }

    public TicketPricePage checkPriceForRoute(Location departLocation, Location arriveLocation) {
        String btnXpath = String.format(btnOptionXpath, TimetableFeature.CHECK_PRICE.getUrl());
        String fullXPath = buildRowXpathForDepartureAndArrival(departLocation, arriveLocation) + btnXpath;
        Utilities.click(By.xpath(fullXPath));

        return new TicketPricePage();
    }

    public TicketPricePage checkPriceForRoute(TicketPriceInformation ticketPriceInformation) {
        return this.checkPriceForRoute(ticketPriceInformation.getDepartLocation(), ticketPriceInformation.getArriveLocation());
    }

    public TicketPricePage checkPriceForRoute(TicketInformation ticketInformation) {
        return this.checkPriceForRoute(ticketInformation.getDepartLocation(), ticketInformation.getArriveLocation());
    }

    public BookTicketPage bookTicketForRoute(Location departLocation, Location arriveLocation) {
        String btnXpath = String.format(btnOptionXpath, TimetableFeature.BOOK_TICKET.getUrl());
        String fullXPath = buildRowXpathForDepartureAndArrival(departLocation, arriveLocation) + btnXpath;
        Utilities.click(By.xpath(fullXPath));

        return new BookTicketPage();
    }

    public BookTicketPage bookTicketForRoute(TicketPriceInformation ticketPriceInformation) {
        return this.bookTicketForRoute(ticketPriceInformation.getDepartLocation(), ticketPriceInformation.getArriveLocation());
    }

    public BookTicketPage bookTicketForRoute(TicketInformation ticketInformation) {
        return this.bookTicketForRoute(ticketInformation.getDepartLocation(), ticketInformation.getArriveLocation());
    }
}
