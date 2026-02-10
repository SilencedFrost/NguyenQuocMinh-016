package DataObjects.Railway;

import Common.Common.RandomUtils;
import Common.Constant.Railway.AccountDefault;

public class UserAccount {

    private String username;
    private String domain;
    private String password;
    private String pid;

    // Returns a UserAccount object with default information
    public UserAccount() {
        this(AccountDefault.EMAIL_VALID, AccountDefault.PASSWORD_VALID);
    }

    public UserAccount(String email, String password) {
        this(email, password, AccountDefault.PID_VALID);
    }

    public UserAccount(String email, String password, String pid) {
        this.setEmail(email);
        this.password = password;
        this.pid = pid;
    }

    public UserAccount(String username, String domain, String password, String pid) {
        this.username = username;
        this.domain = domain;
        this.password = password;
        this.pid = pid;
    }

    // Return a UserAccount object with randomized information
    public UserAccount getRandomUser(String emailDomain) {
        this.setEmail(RandomUtils.generateRandomEmail(emailDomain));
        this.password = RandomUtils.generateRandomPassword();
        this.pid = RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 12);
        return this;
    }

    public UserAccount getRandomUser() {
        this.setEmail(RandomUtils.generateRandomEmail());
        this.password = RandomUtils.generateRandomPassword();
        this.pid = RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 12);
        return this;
    }

    public String getEmail() {
        if(this.username.isEmpty() || this.domain.isEmpty()) return "";
        return this.username + "@" + this.domain;
    }

    public String getUsername() {
        return this.username;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPid() {
        return this.pid;
    }

    protected void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            this.username = "";
            this.domain = "";
            return;
        }

        int atIndex = email.indexOf('@');

        if (atIndex == -1 || atIndex == 0 || atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.username = email.substring(0, atIndex);
        this.domain = email.substring(atIndex + 1);
    }
}
