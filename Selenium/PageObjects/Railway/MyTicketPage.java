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
    private static final String tblXpath = "//div[@class='DivTable']/table";
    private static final String rowXpath = "//tr";
    private static final String tblRowSelectorByHeaderAndValueXpathFragment = "[./td[position() = count(//th[.='%s']/preceding-sibling::th) + 1 and .='%s']]";
    private static final String btnDeleteXpath = "//input[@type='button' and @value='Cancel']";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']/parent::li[@class='selected']"));
    }

    public MyTicketPage deleteTicketWhereDepartAndArriveLocationIs(Location departLocation, Location arriveLocation) {
        String departLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.DEPART_STATION.getText(), departLocation.getText());
        String arriveLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.ARRIVE_STATION.getText(), arriveLocation.getText());

        String fullXpath = tblXpath + rowXpath + departLocationXpathFragment + arriveLocationXpathFragment + btnDeleteXpath;

        Utilities.click(By.xpath(fullXpath));

        WaitUtils.waitForAlertPresent();

        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();

        return this;
    }

    public boolean isRowWhereDepartAndLocationIsPresent(Location departLocation, Location arriveLocation) {
        String departLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.DEPART_STATION.getText(), departLocation.getText());
        String arriveLocationXpathFragment = String.format(tblRowSelectorByHeaderAndValueXpathFragment, TimetableHeader.ARRIVE_STATION.getText(), arriveLocation.getText());

        String fullXpath = tblXpath + rowXpath + departLocationXpathFragment + arriveLocationXpathFragment;

        return Utilities.isElementPresent(By.xpath(fullXpath));
    }
}
