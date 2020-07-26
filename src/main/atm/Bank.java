package atm;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    // Name of the Bank
    private String bankName;
    // list of accounts associated with the bank
    private ArrayList<Account> accounts;
    // list of users associated with the bank
    private ArrayList<UserInfo> userList;
    // unique ID given out to all accounts and Users
    private String universalUniqueID;

    /* Creates new Bank with bank name and an empty list of all
     the accounts in the bank and the users
     */

    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<Account>();
        this.userList = new ArrayList<UserInfo>();
    }

    // Generates a UUID for the user
    public String getNewUserUUId() {

        return universalUniqueID = UUID.randomUUID().toString();
    }

    // Generates a UUID for the account
    public String getNewAccountUUId() {

        return universalUniqueID = UUID.randomUUID().toString();
    }

    // Returns Bank name
    public String getName() {
        return this.bankName;
    }


    // adds account to Bank's accounts ArrayList
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    /* creates new user with:
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
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;

    }

    public UserInfo loginService(String username, int pin) {
        for (UserInfo user : this.userList) {
            if (user.getUsername().compareTo(username) == 0 && user.validPassword(pin)) {
                return user;
            }
        }
        return null;
    }

}
