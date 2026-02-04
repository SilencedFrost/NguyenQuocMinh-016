package DataObjects.Railway;

public class UserAccount {

    private String email = "minhnguyenq2006@gmail.com";
    private String password = "DefaultP4$$";
    private String pid = "12345678";


    public UserAccount(String email, String password, String pid) {
        this.email = email;
        this.password = password;
        this.pid = pid;
    }

    public UserAccount() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPid() {
        return this.pid;
    }
}
