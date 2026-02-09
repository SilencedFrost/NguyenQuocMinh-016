package Common.Constant.Railway;

public enum MenuItem {

    HOME("../"),
    FAQ("/Page/FAQ.cshtml"),
    TIMETABLE("TrainTimeListPage.cshtml"),
    TICKET_PRICE("/Page/TrainPriceListPage.cshtml"),
    BOOK_TICKET("/Page/BookTicketPage.cshtml"),
    REGISTER("/Account/Register.cshtml"),
    LOGIN("/Account/Login.cshtml");

    private final String href;

    MenuItem(String href) {
        this.href = href;
    }

    public String getHref() {
        return this.href;
    }
}
