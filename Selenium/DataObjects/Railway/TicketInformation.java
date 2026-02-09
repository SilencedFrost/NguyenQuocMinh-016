package DataObjects.Railway;

import Common.Constant.Railway.Location;
import Common.Constant.Railway.SeatType;

import java.time.LocalDate;

public class TicketInformation {

    private LocalDate departDate;
    private Location departLocation;
    private Location arriveLocation;
    private SeatType seatType;
    private Integer ticketAmount;


    public TicketInformation(LocalDate departDate, Location departLocation, Location arriveLocation, SeatType seatType, Integer ticketAmount) {
        this.departDate = departDate;
        this.departLocation = departLocation;
        this.arriveLocation = arriveLocation;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public LocalDate getDepartDate() {
        return this.departDate;
    }

    public Location getDepartLocation() {
        return this.departLocation;
    }

    public Location getArriveLocation() {
        return this.arriveLocation;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public Integer getTicketAmount() {
        return this.ticketAmount;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public void setSeatType(String text) {
        for (SeatType type : SeatType.values()) {
            if (type.getText().equals(text)) {
                this.seatType = type;
                return;
            }
        }
        throw new IllegalArgumentException("No SeatType found with text: " + text);
    }
}
