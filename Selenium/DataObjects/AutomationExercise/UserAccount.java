package DataObjects.AutomationExercise;

import Common.Common.RandomUtils;

public class UserAccount {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobileNumber;

    public UserAccount(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = RandomUtils.generateRandomPassword();
        this.address = RandomUtils.generateRandomString(RandomUtils.LOWERCASE_ALPHA, 10);
        this.country = "India";
        this.state = RandomUtils.generateRandomString(RandomUtils.LOWERCASE_ALPHA, 10);
        this.city = RandomUtils.generateRandomString(RandomUtils.LOWERCASE_ALPHA, 10);
        this.zipcode = RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 10);
        this.mobileNumber = RandomUtils.generateRandomString(RandomUtils.NUMERICAL, 10);
    }

    public UserAccount(String email) {
        this(
                RandomUtils.generateRandomString(RandomUtils.LOWERCASE_ALPHA, 10),
                RandomUtils.generateRandomString(RandomUtils.LOWERCASE_ALPHA, 10),
                email
        );
    }

    public UserAccount() {
        this(RandomUtils.generateRandomEmail());
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCountry() {
        return this.country;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }
}
