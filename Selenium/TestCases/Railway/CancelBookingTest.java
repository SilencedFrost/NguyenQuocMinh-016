package TestCases.Railway;

import BusinessFlow.Railway.RegisterAccountFlow;
import Common.Constant.EmailDomain;
import Common.Constant.Railway.Location;
import Common.Constant.Railway.MenuItem;
import Common.Constant.Railway.SeatType;
import DataObjects.Railway.TicketInformation;
import DataObjects.Railway.UserAccount;
import PageObjects.Railway.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class CancelBookingTest extends BaseTest{

    @Test
    public void TC16() {
        log.info("TC16 - User can cancel a ticket");

        // Data
        UserAccount userAccount = new UserAccount().getRandomUser(EmailDomain.GUERRILLA);
        TicketInformation ticketInformation = new TicketInformation(LocalDate.now().plusDays(5), Location.SAIGON, Location.PHAN_THIET, SeatType.SOFT_BED_AC, 2);

        // Actions
        log.info("Pre-condition: an activated account is existing");
        log.info("1. Navigate to QA Railway Website");
        RegisterPage registerPage = RegisterAccountFlow.registerAndActivate(userAccount);

        log.info("2. Login with a valid account");
        HomePage homePage = ((LoginPage) registerPage.gotoPage(MenuItem.LOGIN)).login(userAccount.getEmail(), userAccount.getPassword());

        log.info("3. Book a ticket");
        BookTicketPage bookTicketPage = ((BookTicketPage) homePage.gotoPage(MenuItem.BOOK_TICKET)).bookTicket(ticketInformation);

        log.info("4. Click on \"My ticket\" tab");
        MyTicketPage myTicketPage = (MyTicketPage) bookTicketPage.gotoPage(MenuItem.MY_TICKET);

        log.info("5. Click on \"Cancel\" button of ticket which user want to cancel.");
        log.info("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
        myTicketPage.deleteTicketForRoute(ticketInformation.getDepartLocation(), ticketInformation.getArriveLocation());

        log.info("The canceled ticket is disappeared.");
        Assert.assertFalse(myTicketPage.isRowForRoutePresent(ticketInformation.getDepartLocation(), ticketInformation.getArriveLocation()), "Deleted ticket is still present");
    }
}
