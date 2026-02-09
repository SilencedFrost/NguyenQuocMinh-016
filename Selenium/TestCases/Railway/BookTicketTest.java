package TestCases.Railway;

import BusinessFlow.Railway.RegisterAccountFlow;
import Common.Constant.Constant;
import Common.Constant.EmailDomain;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.MenuItem;
import Common.Constant.Railway.SeatType;
import Common.Constant.Railway.TicketHeader;
import DataObjects.Railway.TicketInformation;
import DataObjects.Railway.TicketPriceInformation;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

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
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount);

        log.info("2. Login with a valid account");
        HomePage homePage = ((LoginPage) registerPage.gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword());

        log.info("3. Click on \"Book ticket\" tab");
        BookTicketPage bookTicketPage = (BookTicketPage) homePage.gotoPage(MenuItem.BOOK_TICKET);

        log.info("4. Select the next 2 days from \"Depart date\"");
        ticketInformation.setDepartDate(bookTicketPage.getDepartDate().plusDays(nextDays));

        log.info("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
        log.info("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
        log.info("7. Select \"1\" for \"Ticket amount\"");
        log.info("8. Click on \"Book ticket\" button");
        bookTicketPage.bookTicket(ticketInformation);

        // Assertions
        log.info("Message \"Ticket booked successfully!\" displays.");
        String actualMsg = bookTicketPage.getTitle();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Confirm message is not displayed as expected");

        log.info("Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualDepartDate = bookTicketPage.getCellValue(TicketHeader.DEPART_DATE);
        String actualDepartStation = bookTicketPage.getCellValue(TicketHeader.DEPART_STATION);
        String actualArriveStation = bookTicketPage.getCellValue(TicketHeader.ARRIVE_STATION);
        String actualSeatType = bookTicketPage.getCellValue(TicketHeader.SEAT_TYPE);
        String actualAmount = bookTicketPage.getCellValue(TicketHeader.AMOUNT);

        Assert.assertEquals(actualDepartDate, ticketInformation.getDepartDate().format(Constant.DATE_FORMAT));
        Assert.assertEquals(actualDepartStation, ticketInformation.getDepartLocation().getText());
        Assert.assertEquals(actualArriveStation, ticketInformation.getArriveLocation().getText());
        Assert.assertEquals(actualSeatType, ticketInformation.getSeatType().getText());
        Assert.assertEquals(actualAmount, ticketInformation.getTicketAmount().toString());
    }

    @Test
    public void TC13() {
        log.info("TC13 - User can book many tickets at a time");

        // Data
        UserAccount userAccount = new UserAccount().getRandomUser(EmailDomain.GUERRILLA);
        TicketInformation ticketInformation = new TicketInformation(null, Location.NHA_TRANG, Location.SAIGON, SeatType.SOFT_SEAT_AC, 5);
        int nextDays = 25;
        String expectedMsg = "Ticket booked successfully!";

        // Actions
        log.info("Pre-condition: an activated account is existing");
        log.info("1. Navigate to QA Railway Website");
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount);

        log.info("2. Login with a valid account");
        HomePage homePage = ((LoginPage) registerPage.gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword());

        log.info("3. Click on \"Book ticket\" tab");
        BookTicketPage bookTicketPage = (BookTicketPage) homePage.gotoPage(MenuItem.BOOK_TICKET);

        log.info("4. Select the next 25 days from \"Depart date\"");
        ticketInformation.setDepartDate(bookTicketPage.getDepartDate().plusDays(nextDays));

        log.info("5. Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\".");
        log.info("6. Select \"Soft seat with air conditioner\" for \"Seat type\"");
        log.info("7. Select \"5\" for \"Ticket amount\"\n");
        log.info("8. Click on \"Book ticket\" button");
        bookTicketPage.bookTicket(ticketInformation);

        // Assertions
        log.info("Message \"Ticket booked successfully!\" displays.");
        String actualMsg = bookTicketPage.getTitle();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Confirm message is not displayed as expected");

        log.info("Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualDepartDate = bookTicketPage.getCellValue(TicketHeader.DEPART_DATE);
        String actualDepartStation = bookTicketPage.getCellValue(TicketHeader.DEPART_STATION);
        String actualArriveStation = bookTicketPage.getCellValue(TicketHeader.ARRIVE_STATION);
        String actualSeatType = bookTicketPage.getCellValue(TicketHeader.SEAT_TYPE);
        String actualAmount = bookTicketPage.getCellValue(TicketHeader.AMOUNT);

        Assert.assertEquals(actualDepartDate, ticketInformation.getDepartDate().format(Constant.DATE_FORMAT), "Actual depart date is not displayed as expected");
        Assert.assertEquals(actualDepartStation, ticketInformation.getDepartLocation().getText(), "Actual depart station is not displayed as expected");
        Assert.assertEquals(actualArriveStation, ticketInformation.getArriveLocation().getText(), "Actual arrival station is not displayed as expected");
        Assert.assertEquals(actualSeatType, ticketInformation.getSeatType().getText(), "Actual seat type is not displayed as expected");
        Assert.assertEquals(actualAmount, ticketInformation.getTicketAmount().toString(), "Actual ticket amount is not displayed as expected");
    }

    @Test
    public void TC14() {
        log.info("TC14 - User can check price of ticket from Timetable");

        // Data
        UserAccount userAccount = new UserAccount().getRandomUser(EmailDomain.GUERRILLA);
        TicketPriceInformation ticketPriceInformation = new TicketPriceInformation(Location.DA_NANG, Location.SAIGON, "Ticket price from Đà Nẵng to Sài Gòn", 310000, 335000, 360000, 410000, 460000, 510000);

        // Actions
        log.info("Pre-condition: an activated account is existing");
        log.info("1. Navigate to QA Railway Website");
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount);

        log.info("2. Login with a valid account");
        HomePage homePage = ((LoginPage) registerPage.gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword());

        log.info("3. Click on \"Timetable\" tab");
        TimetablePage timetablePage = (TimetablePage) homePage.gotoPage(MenuItem.TIMETABLE);

        log.info("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
        TicketPricePage ticketPricePage = timetablePage.checkPriceForRoute(ticketPriceInformation.getDepartLocation(), ticketPriceInformation.getArriveLocation());

        // Assertions
        log.info("\"Ticket Price\" page is loaded.\"Ticket Price\" page is loaded.");
        Assert.assertTrue(ticketPricePage.isPageShown(), "Ticket price page is not displayed as expected");

        log.info("Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".");
        String actualTableTitle = ticketPricePage.getTableHeader();

        Assert.assertEquals(actualTableTitle, ticketPriceInformation.getTitle(), "Ticket table title is not displayed as expected");

        log.info("Price for each seat displays correctly: HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
        String actualHSPrice = ticketPricePage.getSeatPrice(SeatType.HARD_SEAT);
        String actualSSPrice = ticketPricePage.getSeatPrice(SeatType.SOFT_SEAT);
        String actualSSCPrice = ticketPricePage.getSeatPrice(SeatType.SOFT_SEAT_AC);
        String actualHBPrice = ticketPricePage.getSeatPrice(SeatType.HARD_BED);
        String actualSBPrice = ticketPricePage.getSeatPrice(SeatType.SOFT_BED);
        String actualSBCPrice = ticketPricePage.getSeatPrice(SeatType.SOFT_BED_AC);

        Assert.assertEquals(actualHSPrice, ticketPriceInformation.getExpectedHSPrice().toString(), "Ticket price is not displayed as expected");
        Assert.assertEquals(actualSSPrice, ticketPriceInformation.getExpectedSSPrice().toString(), "Ticket price is not displayed as expected");
        Assert.assertEquals(actualSSCPrice, ticketPriceInformation.getExpectedSSCPrice().toString(), "Ticket price is not displayed as expected");
        Assert.assertEquals(actualHBPrice, ticketPriceInformation.getExpectedHBPrice().toString(), "Ticket price is not displayed as expected");
        Assert.assertEquals(actualSBPrice, ticketPriceInformation.getExpectedSBPrice().toString(), "Ticket price is not displayed as expected");
        Assert.assertEquals(actualSBCPrice, ticketPriceInformation.getExpectedSBCPrice().toString(), "Ticket price is not displayed as expected");
    }

    @Test
    public void TC15() {
        log.info("TC15 - User can book ticket from Timetable");

        // Data
        UserAccount userAccount = new UserAccount().getRandomUser(EmailDomain.GUERRILLA);
        TicketInformation ticketInformation = new TicketInformation(LocalDate.now().plusDays(1), Location.QUANG_NGAI, Location.HUE, null, 5);
        String expectedMsg = "Ticket booked successfully!";

        // Actions
        log.info("Pre-condition: an activated account is existing");
        log.info("1. Navigate to QA Railway Website");
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount);

        log.info("2. Login with a valid account");
        HomePage homePage = ((LoginPage) registerPage.gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword());

        log.info("3. Click on \"Timetable\" tab");
        TimetablePage timetablePage = (TimetablePage) homePage.gotoPage(MenuItem.TIMETABLE);

        log.info("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
        BookTicketPage bookTicketPage = timetablePage.bookTicketForRoute(ticketInformation.getDepartLocation(), ticketInformation.getArriveLocation());

        // Assertions
        log.info("Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");

        Assert.assertTrue(bookTicketPage.isPageShown(), "Book ticket page is not displayed as expected");
        Assert.assertEquals(bookTicketPage.getDepartLocation(), ticketInformation.getDepartLocation().getText(), "Depart location is not displayed as expected");
        Assert.assertEquals(bookTicketPage.getArriveLocation(), ticketInformation.getArriveLocation().getText(), "Arrive location is not displayed as expected");

        // Get default seat type to verify later
        ticketInformation.setSeatType(bookTicketPage.getSeatType());

        // Actions
        log.info("5. Select Depart date = tomorrow");
        log.info("6. Select Ticket amount = 5");
        log.info("7. Click on \"Book ticket\" button");
        bookTicketPage.bookTicket(ticketInformation.getDepartDate(), null, null, null, ticketInformation.getTicketAmount());

        // Assertions
        log.info("Message \"Ticket booked successfully!\" displays.");
        String actualMsg = bookTicketPage.getTitle();

        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Confirm message is not displayed as expected");

        log.info("Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualDepartDate = bookTicketPage.getCellValue(TicketHeader.DEPART_DATE);
        String actualDepartStation = bookTicketPage.getCellValue(TicketHeader.DEPART_STATION);
        String actualArriveStation = bookTicketPage.getCellValue(TicketHeader.ARRIVE_STATION);
        String actualSeatType = bookTicketPage.getCellValue(TicketHeader.SEAT_TYPE);
        String actualAmount = bookTicketPage.getCellValue(TicketHeader.AMOUNT);

        Assert.assertEquals(actualDepartDate, ticketInformation.getDepartDate().format(Constant.DATE_FORMAT));
        Assert.assertEquals(actualDepartStation, ticketInformation.getDepartLocation().getText());
        Assert.assertEquals(actualArriveStation, ticketInformation.getArriveLocation().getText());
        Assert.assertEquals(actualSeatType, ticketInformation.getSeatType().getText());
        Assert.assertEquals(actualAmount, ticketInformation.getTicketAmount().toString());
    }
}
