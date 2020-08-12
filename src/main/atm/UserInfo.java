package atm;

import exceptions.OnlyPositiveException;

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


    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: set user's information
    //    information includes the following
    //    - first name
    //    - last name
    //    - username
    //    - password
    //    - uuid
    //    - empty list of accounts
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
        // Source - https://matthew-brett.github.io/teaching/string_formatting.html
        /*
        System.out.printf("Welcome %s %s, your new user ID %s has been created with pin %s"
                        + "\n and username: %s \n\n",
                firstName, lastName, uuid, password, username);

         */


    }


    // REQUIRES: Account
    // MODIFIES: this
    // EFFECTS: Adds account to the list of accounts associated with this user
    public void addAccount(Account newAccount) {
        this.accounts.add(newAccount);
    }

    // Returns Users First Name
    public String getFirstName() {
        return this.firstName;
    }

    // returns last name of the user
    public String getLastName() {
        return this.lastName;
    }

    // returns the users password
    public int getPassword() {
        return this.password;
    }


    // REQUIRES: pin
    // EFFECTS: produce true if pin matches password in system, false otherwise
    public boolean validPassword(int pin) {
        return pin == password;
    }

    // retrieve user's username
    public String getUsername() {
        return this.username;
    }

    // Source - https://www.youtube.com/watch?v=k0BofouWX-o
    // Source - https://matthew-brett.github.io/teaching/string_formatting.html
    // REQUIRES: Account
    // EFFECTS: prints a summary of all the accounts associated with the user
    public void accountSummary() {
        System.out.println("\n\n                                    Today's account summary\n");
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


    // REQUIRES: transaction
    // MODIFIES this
    // EFFECTS: Adds transaction to selected account
    public void addAcctTransaction(int acct, double amount) throws OnlyPositiveException {
        if (amount <= 0) {
            throw new OnlyPositiveException();
        } else {
            this.accounts.get(acct).addTransaction(amount);
        }
    }

}
