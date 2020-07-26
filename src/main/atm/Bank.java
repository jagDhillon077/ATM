package atm;

import java.util.ArrayList;
import java.util.UUID;
// ideas on how to create a bank and account
// Source - https://www.youtube.com/watch?v=mp1_F7lfmNE

public class Bank {
    // Name of the Bank
    private String bankName;
    // list of accounts associated with the bank
    private ArrayList<Account> accounts;
    // list of users associated with the bank
    private ArrayList<UserInfo> userList;
    // unique ID given out to all accounts and Users
    private String universalUniqueID;


    // MODIFIES: this
    // EFFECTS:  Creates new Bank with bank name and an empty list of all
    //     the accounts in the bank and the user
    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    // UUID Source - https://docs.oracle.com/javase/7/docs/api/
    // Source # 2 - https://www.baeldung.com/java-uuid#:~:text=The%20randomUUID(
    // )%20method%20creates,fromString(String%20uuidHexDigitString)%3B
    // Generates a UUID for the user
    // Source # 3 - https://stackoverflow.com/questions/1389736/how-do-i-create-a-unique-id-in-java
    public String getNewUserUUId() {
        return universalUniqueID = UUID.randomUUID().toString();
    }


    // Generates a UUID for the account
    public String getNewAccountUUId() {
        return universalUniqueID = UUID.randomUUID().toString();
    }

    // REQUIRES: Account
    // MODIFIES: this
    // EFFECTS: adds account to Bank's accounts ArrayList
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }


    // MODIFIES: this
    /* EFFECTS: creates a new user with
    - first name
    - last name
    - username
    - pin
     */
    public UserInfo newUser(String firstname, String lastName, String username, int pin) {

        // user is created and added to the banks userList
        UserInfo newUser = new UserInfo(firstname, lastName, username, this, pin);
        this.userList.add(newUser);

        // create a checking account for the user
        Account newAccount = new Account("Checking", 0, this, newUser, bankName);
        Account newAccount2 = new Account("Saving", 0, this, newUser, bankName);
        this.addAccount(newAccount);
        this.addAccount(newAccount2);

        return newUser;

    }

    // login system that checks if username and password match user credentials, returning user if true, else null
    // loginService Source -
    // https://stackoverflow.com/questions/16627910/how-to-code-a-very-simple-login-system-with-java
    // REQUIRES: String and Pin for User
    // EFFECTS: Allowed login of a user if the string and pin match any of string and pin combinations in
    // system
    public UserInfo loginService(String username, int pin) {
        for (UserInfo passwordProtectedDetails : this.userList) {
            if (passwordProtectedDetails.getUsername().compareTo(username)
                    == 0 && passwordProtectedDetails.validPassword(pin)) {
                return passwordProtectedDetails;
            }
        }
        return null;
    }

}
