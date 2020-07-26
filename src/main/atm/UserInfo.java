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
        System.out.printf("Welcome %s %s, your new user ID %s has been created with pin %s.\n",
                firstName, lastName, uuid, password);


    }


    // Adds account to list of accounts associated with just the user
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    // Returns Users First Name
    public String getFirstName() {
        return this.firstName;
    }


    public String getUuid() {
        return this.uuid;
    }

    public boolean validPassword(int pin) {
        return pin == 111111;
    }


    public void accountSummary() {
        System.out.printf("\n\n%s's account summary", this.firstName);
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.printf("%d) %s\n", i + 1,
                    this.accounts.get(i).getSummaryLine());
        }
        System.out.println();
    }

    // return the amount of the accounts the user has
    public int numAccounts() {
        return this.accounts.size();
    }

    /*
    Print transaction history for selected account
     */
    public void printAccountHistory(int acct) {
        this.accounts.get(acct).printTransactionHistory();
    }

    /*
    Gets account balance
     */
    public double getAcctBalance(int acct) {
        return this.accounts.get(acct).getBalance();
    }

    public void addAcctTransaction(int acct, double amount) {
        this.accounts.get(acct).addTransaction(amount);
    }

}
