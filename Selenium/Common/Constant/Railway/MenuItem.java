package Common.Constant.Railway;

public enum MenuItem {

    HOME("../"),
    FAQ("/Page/FAQ.cshtml"),
    BOOK_TICKET("/Page/BookTicketPage.cshtml"),
    REGISTER("/Account/Register.cshtml"),
    LOGIN("/Account/Login.cshtml");

    public final String href;

    MenuItem(String href) {
        this.href = href;
    }

    public String getHref() {
        return this.href;
    }
}
