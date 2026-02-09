package Common.Constant.Railway;

public enum SeatType {
    HARD_SEAT("Hard seat", "HS"),
    SOFT_SEAT("Soft seat", "SS"),
    SOFT_SEAT_AC("Soft seat with air conditioner", "SSC"),
    HARD_BED("Hard bed", "HB"),
    SOFT_BED("Soft bed", "SB"),
    SOFT_BED_AC("Soft bed with air conditioner", "SBC");

    private final String text;
    private final String abbreviation;

    SeatType(String text, String abbreviation) {
        this.text = text;
        this.abbreviation = abbreviation;
    }

    public String getText() {
        return this.text;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
