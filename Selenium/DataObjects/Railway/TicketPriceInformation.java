package DataObjects.Railway;

import Common.Constant.Railway.Location;

public class TicketPriceInformation {

    private Location departLocation;
    private Location arriveLocation;
    private String title;
    private Integer expectedHSPrice;
    private Integer expectedSSPrice;
    private Integer expectedSSCPrice;
    private Integer expectedHBPrice;
    private Integer expectedSBPrice;
    private Integer expectedSBCPrice;

    public TicketPriceInformation(Location departLocation, Location arriveLocation, String title, Integer expectedHSPrice, Integer expectedSSPrice, Integer expectedSSCPrice, Integer expectedHBPrice, Integer expectedSBPrice, Integer expectedSBCPrice) {
        this.departLocation = departLocation;
        this.arriveLocation = arriveLocation;
        this.title = title;
        this.expectedHSPrice = expectedHSPrice;
        this.expectedSSPrice = expectedSSPrice;
        this.expectedSSCPrice = expectedSSCPrice;
        this.expectedHBPrice = expectedHBPrice;
        this.expectedSBPrice = expectedSBPrice;
        this.expectedSBCPrice = expectedSBCPrice;
    }

    public Location getDepartLocation() {
        return this.departLocation;
    }

    public Location getArriveLocation() {
        return this.arriveLocation;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getExpectedHSPrice() {
        return this.expectedHSPrice;
    }

    public Integer getExpectedSSPrice() {
        return this.expectedSSPrice;
    }

    public Integer getExpectedSSCPrice() {
        return this.expectedSSCPrice;
    }

    public Integer getExpectedHBPrice() {
        return this.expectedHBPrice;
    }

    public Integer getExpectedSBPrice() {
        return this.expectedSBPrice;
    }

    public Integer getExpectedSBCPrice() {
        return this.expectedSBCPrice;
    }
}
