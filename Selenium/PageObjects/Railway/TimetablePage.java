package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.TimetableFeature;
import Common.Constant.Railway.TimetableHeader;
import org.openqa.selenium.By;

public class TimetablePage extends GeneralPage{

    // Locators
    private static final String tblXpath = "//div[@class='DivTable']/table";
    private static final String rowXpath = "//tr";
        private static final String tblRowSelectorByHeaderAndValueXpathFragment = "[./td[position() = count(//th[.='%s']/preceding-sibling::th) + 1 and .='%s']]";
    private static final String btnXpathFragment = "//a[contains(@href, '%s')]";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']/parent::li[@class='selected']"));
    }

    private String getTableRowXpathWhereDepartAndArriveLocationIs(Location departLocation, Location arriveLocation) {
        String departLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.DEPART_STATION.getText(), departLocation.getText());
        String arriveLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.ARRIVE_STATION.getText(), arriveLocation.getText());

        return tblXpath + rowXpath + departLocationXpathFragment + arriveLocationXpathFragment;
    }

    public TicketPricePage checkPriceWhereDepartAndArriveLocationIs(Location departLocation, Location arriveLocation) {
        String btnXpath = String.format(btnXpathFragment, TimetableFeature.CHECK_PRICE.getUrl());

        String fullXPath = getTableRowXpathWhereDepartAndArriveLocationIs(departLocation, arriveLocation) + btnXpath;

        Utilities.click(By.xpath(fullXPath));

        return new TicketPricePage();
    }

    public BookTicketPage bookTicketWhereDepartAndArriveLocationIs(Location departLocation, Location arriveLocation) {
        String btnXpath = String.format(btnXpathFragment, TimetableFeature.BOOK_TICKET.getUrl());

        String fullXPath = getTableRowXpathWhereDepartAndArriveLocationIs(departLocation, arriveLocation) + btnXpath;

        Utilities.click(By.xpath(fullXPath));

        return new BookTicketPage();
    }

}
