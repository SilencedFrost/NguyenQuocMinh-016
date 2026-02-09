package Common.Constant.Railway;

public enum Location {

    SAIGON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String text;

    Location(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
