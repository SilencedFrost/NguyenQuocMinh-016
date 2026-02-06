package Common.Constant.Railway;

public enum MenuItem {

    HOME("Home"),
    FAQ("Faq"),
    BOOK_TICKET("Book ticket"),
    REGISTER("Register"),
    LOGIN("Login");

    public final String menuName;

    MenuItem(String menuName) {
        this.menuName = menuName;
    }
}
