package Common.Constant;

public enum MenuItem {

    HOME("Home"),
    FAQ("Faq"),
    REGISTER("Register"),
    LOGIN("Login");

    public final String menuName;

    MenuItem(String menuName) {
        this.menuName = menuName;
    }
}
