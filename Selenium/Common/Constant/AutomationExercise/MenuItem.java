package Common.Constant.AutomationExercise;

public enum MenuItem {

    HOME("Home"),
    PRODUCTS("Products"),
    SIGNUP_LOGIN("Signup / Login");

    private final String text;

    MenuItem(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
