package Common.Constant.AutomationExercise;

public enum RegisterTxt {

    NAME("Name"),
    EMAIL("Email Address"),
    PASSWORD("Password"),
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    ADDRESS("Address"),
    STATE("State"),
    CITY("City"),
    ZIPCODE("Zipcode"),
    MOBILE_NUMBER("Mobile Number");

    private final String text;

    RegisterTxt(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
