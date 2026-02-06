package Common.Constant.Railway;

public enum SeatType {
    HARD_SEAT("Hard seat"),
    SOFT_SEAT("Soft seat"),
    SOFT_SEAT_AC("Soft seat with air conditioner"),
    HARD_BED("Hard bed"),
    SOFT_BED("Soft bed"),
    SOFT_BED_AC("Soft bed with air conditioner");

    public final String visibleText;

    SeatType(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return this.visibleText;
    }
}
