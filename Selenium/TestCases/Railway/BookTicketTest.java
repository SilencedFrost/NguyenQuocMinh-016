package TestCases.Railway;

import BusinessFlow.Railway.RegisterAccountFlow;
import Common.Constant.Constant;
import Common.Constant.EmailDomain;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.MenuItem;
import Common.Constant.Railway.SeatType;
import Common.Constant.Railway.TicketHeader;
import DataObjects.Railway.TicketInformation;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.BookTicketPage;
import PageObjects.Railway.HomePage;
import PageObjects.Railway.LoginPage;
import PageObjects.Railway.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookTicketTest extends BaseTest{

    @Test
    public void TC12() {
        log.info("TC12 - User can book 1 ticket at a time");

        // Data
        UserAccount userAccount = new UserAccount().getRandomUser(EmailDomain.GUERRILLA);
        TicketInformation ticketInformation = new TicketInformation(null, Location.NHA_TRANG, Location.HUE, SeatType.SOFT_BED_AC, 1);
        int nextDays = 2;
        String expectedMsg = "Ticket booked successfully!";

        // Actions
        log.info("Pre-condition: an activated account is existing");
        log.info("1. Navigate to QA Railway Website");
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount.getUsername(), userAccount.getEmail(), userAccount.getPassword(), userAccount.getPid());

        log.info("2. Login with a valid account");
        HomePage homePage = ((LoginPage) registerPage.gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword()).expectSuccess();

        log.info("3. Click on \"Book ticket\" tab");
        BookTicketPage bookTicketPage = (BookTicketPage) homePage.gotoPage(MenuItem.BOOK_TICKET);

        log.info("4. Select the next 2 days from \"Depart date\"");
        ticketInformation.setDepartDate(bookTicketPage.getDepartDate().plusDays(nextDays));

        log.info("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
        log.info("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
        log.info("7. Select \"1\" for \"Ticket amount\"");
        log.info("8. Click on \"Book ticket\" button");
        bookTicketPage.bookTicket(
                ticketInformation.getDepartDate(),
                ticketInformation.getDepartLocation(),
                ticketInformation.getArriveLocation(),
                ticketInformation.getSeatType(),
                ticketInformation.getTicketAmount());

        // Assertions
        log.info("Message \"Ticket booked successfully!\" displays.");
        String actualMsg = bookTicketPage.getTitle();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Error message is not displayed as expected");

        log.info("Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualDepartDate = bookTicketPage.getCellValue(TicketHeader.DEPART_DATE);
        String actualDepartStation = bookTicketPage.getCellValue(TicketHeader.DEPART_STATION);
        String actualArriveStation = bookTicketPage.getCellValue(TicketHeader.ARRIVE_STATION);
        String actualSeatType = bookTicketPage.getCellValue(TicketHeader.SEAT_TYPE);
        String actualAmount = bookTicketPage.getCellValue(TicketHeader.AMOUNT);

        Assert.assertEquals(actualDepartDate, ticketInformation.getDepartDate().format(Constant.DATE_FORMAT));
        Assert.assertEquals(actualDepartStation, ticketInformation.getDepartLocation().getVisibleText());
        Assert.assertEquals(actualArriveStation, ticketInformation.getArriveLocation().getVisibleText());
        Assert.assertEquals(actualSeatType, ticketInformation.getSeatType().getVisibleText());
        Assert.assertEquals(actualAmount, ticketInformation.getTicketAmount().toString());
    }
}
