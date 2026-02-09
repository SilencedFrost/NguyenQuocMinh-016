package Common.Constant.Railway;

public enum TimetableHeader {
    NUMBER("No."),
    DEPART_STATION("Depart Station"),
    ARRIVE_STATION("Arrive Station"),
    DEPART_TIME("Depart TIme"),
    ARRIVE_TIME("Arrive time");

    private final String text;

    TimetableHeader(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
