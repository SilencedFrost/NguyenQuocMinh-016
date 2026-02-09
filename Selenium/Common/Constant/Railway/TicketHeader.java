package Common.Constant.Railway;

public enum TicketHeader {
    DEPART_STATION("Depart Station"),
    ARRIVE_STATION("Arrive Station"),
    SEAT_TYPE("Seat Type"),
    DEPART_DATE("Depart Date"),
    BOOK_DATE("Book Date"),
    EXPIRED_DATE("Expired Date"),
    AMOUNT("Amount"),
    TOTAL_PRICE("Total Price");

    public final String text;

    TicketHeader(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
