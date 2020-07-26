package atm;

import java.sql.SQLOutput;
import java.util.ArrayList;
// Information about the user logging into the ATM application

public class UserInfo {

    // User's first name
    private String firstName;

    // User's last name
    private String lastName;

    // User's login name
    private String username;

    // User's password
    private int password;

    // User's UUID (Universally Unique Identifier, see README.md)
    private String uuid;


    // List of Accounts that are owned by the user
    private ArrayList<Account> accounts;

    /* set user's information
    information includes the following
    - first name
    - last name
    - username
    - password
    - uuid
    - empty list of accounts
     */

    public UserInfo(String firstName, String lastName, String username, Bank userBank, int password) {
        // initialize user fields
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

        // creates UUID for user
        this.uuid = userBank.getNewUserUUId();

        // starts the user off with no current accounts
        this.accounts = new ArrayList<Account>();

        // prints message describing all the user information.
        System.out.printf("Welcome %s %s, your new user ID %s has been created with pin %s"
                        + "\n and username: %s \n\n",
                firstName, lastName, uuid, password, username);


    }


    // Adds account to list of accounts associated with just the user
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    // Returns Users First Name
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Bank getBank() {
        return this.getBank();
    }

    public int getPassword() {
        return this.password;
    }


    public String getUuid() {
        return this.uuid;
    }

    public boolean validPassword(int pin) {
        return pin == password;
    }

    public String getUsername() {
        return this.username;
    }


    public void accountSummary() {
        System.out.printf("\n\n%s's account summary\n", this.firstName);
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.printf("%d) %s\n", i + 1,
                    this.accounts.get(i).getSummaryLine());
        }
        System.out.println();
    }



    // Gets account balance
    public double getAcctBalance(int acct) {
        return this.accounts.get(acct).getBalance();
    }

    // Adds transaction to selected account
    public void addAcctTransaction(int acct, double amount) {
        this.accounts.get(acct).addTransaction(amount);
    }

}
