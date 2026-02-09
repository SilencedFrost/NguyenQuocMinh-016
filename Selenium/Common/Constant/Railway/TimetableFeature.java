package Common.Constant.Railway;

public enum TimetableFeature {
    CHECK_PRICE("check price", "TicketPricePage.cshtml"),
    BOOK_TICKET("book ticket", "BookTicketPage.cshtml");

    private final String text;
    private final String url;

    TimetableFeature(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return this.text;
    }

    public String getUrl() {
        return  this.url;
    }
}
