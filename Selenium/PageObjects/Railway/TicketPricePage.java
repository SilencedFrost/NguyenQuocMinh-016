package PageObjects.Railway;

import Common.Common.Utilities;
import Common.Constant.Railway.SeatType;
import com.google.common.util.concurrent.ClosingFuture;
import org.openqa.selenium.By;

public class TicketPricePage extends GeneralPage{

    // Locators
    private final By lblTableHeaderLocator = By.xpath("//div[@class='DivTable']/table//th[@colspan = '7']");
    private final String tblCellBySeatTypeXpath = "//div[@class='DivTable']/table//tr[./th[contains(., 'Price')]]/td[position() = count(//tr[./th[contains(., 'Seat type')]]/td[.='%s']/preceding-sibling::td) + 1]";

    // Methods
    @Override
    public boolean isPageShown() {
        return Utilities.isElementPresent(By.xpath("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']/parent::li[@class='selected']"));
    }

    public String getTableHeader() {
        return Utilities.findElement(lblTableHeaderLocator).getText();
    }

    public String getSeatPrice(SeatType seatType) {
        return Utilities.findElement(By.xpath(String.format(tblCellBySeatTypeXpath, seatType.getAbbreviation()))).getText();
    }
}
