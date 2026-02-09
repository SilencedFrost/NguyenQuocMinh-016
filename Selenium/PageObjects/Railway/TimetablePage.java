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

    public TicketPricePage checkPriceWhereDepartAndArriveLocationIs(Location departLocation, Location arriveLocation) {
        String departLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.DEPART_STATION.getText(), departLocation.getVisibleText());
        String arriveLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.ARRIVE_STATION.getText(), arriveLocation.getVisibleText());
        String btnXpath = String.format(btnXpathFragment, TimetableFeature.CHECK_PRICE.getUrl());

        String fullXPath = tblXpath + rowXpath + departLocationXpathFragment + arriveLocationXpathFragment + btnXpath;

        Utilities.click(By.xpath(fullXPath));

        return new TicketPricePage();
    }

}
