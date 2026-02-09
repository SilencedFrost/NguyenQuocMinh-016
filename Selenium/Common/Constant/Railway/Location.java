package Common.Constant.Railway;

public enum Location {

    SAIGON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String visibleText;

    Location(String visibleText) {
        this.visibleText = visibleText;
    }

    public String getVisibleText() {
        return this.visibleText;
    }
}
