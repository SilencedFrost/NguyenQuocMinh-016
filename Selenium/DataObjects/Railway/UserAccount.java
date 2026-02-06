package DataObjects.Railway;

import Common.Common.RandomUtils;
import Common.Constant.EmailDomain;

public class UserAccount {

    private String username = "minhnguyenq2006";
    private String domain = EmailDomain.GMAIL;
    private String password = "DefaultP4$$";
    private String pid = "12345678";


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

    /**
     * Returns a UserAccount object with default information
     */
    public UserAccount() {
    }

    /**
     * @return a UserAccount object with randomized information
     */
    public UserAccount getRandomUser(String emailDomain) {
        this.username = RandomUtils.generateRandomString(15);
        this.domain = emailDomain;
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

    public void setEmail(String email) {
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
