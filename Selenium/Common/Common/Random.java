package Common.Common;

import java.security.SecureRandom;

public class Random {

    //Charsets
    public static final String LOWERCASE_ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMERICAL = "0123456789";
    public static final String SPECIAL_CHARACTERS = "! @ # $ % ^ & * ( ) - _ = + [ ] { } \\ | ; : ' \" , . < > / ? ~ `";

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(String charset, Integer length) {
        if (length == null || length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer");
        }

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length());
            result.append(charset.charAt(index));
        }

        return result.toString();
    }

    /**
     * Returns random string with set length, contains only alphanumeric
     * @param length length of generated string
     * @return random string
     */
    public static String generateRandomString(Integer length) {
        return generateRandomString(Random.LOWERCASE_ALPHA + Random.UPPERCASE_ALPHA + Random.NUMERICAL, length);
    }

    /**
     * Returns password containing: uppercase letter, lowercase letter, numbers, and special characters that is 14 characters long
     * @return random password
     */
    public static String generateRandomPassword() {
        return "1Wa$" + generateRandomString(Random.LOWERCASE_ALPHA + Random.UPPERCASE_ALPHA + Random.NUMERICAL + Random.SPECIAL_CHARACTERS, 10);
    }

    public static String generateRandomEmail() {
        // Generate random username (8-12 characters)
        int usernameLength = 8 + random.nextInt(5); // 8 to 12
        String username = generateRandomString(usernameLength);

        // Common email domains
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};
        String domain = domains[random.nextInt(domains.length)];

        return username + "@" + domain;
    }
}
