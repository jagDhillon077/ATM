package atm;


import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Account that can be accessed and modified by the account owner.
// ideas on how to create a bank and account
// Source - https://www.youtube.com/watch?v=mp1_F7lfmNE


public class Account implements Saveable {

    // What type of account is this, ie (Checking, Saving, Emergency...)
    private String type;
    // Balance in account
    private double balance;
    // Provides the UserInfo of who owns this certain account
    private UserInfo owner;
    // The accounts Universally Unique Identifier
    private String uuid;
    // List that remembers the transactions made to and from this account
    private ArrayList<Transaction> transactions;
    //=========================================================
    // section dedicated to the construction of saveable accounts;
    // the account owner name
    private String name = "";
    // the balance of the account
    private int initialBalance;
    //=========================================================



    /*
    Creates new Account with Account type, balance, Bank the account is associated with
    and the account owner
     */
    // MODIFIES: this
    // EFFECTS: creates an Account (Checking or Saving), with balance, owner of account, UUID, and list of Transactions
    public Account(String type, int balance, Bank bank, UserInfo owner, String bankName) {

        if (balance >= 0) {
            balance = initialBalance;
        } else {
            balance = 0;
        }

        // sets account type and the owner
        this.type = type;
        this.owner = owner;
        this.balance = balance;
        // gets UUID for account
        this.uuid = bank.getNewAccountUUId();

        // initialize an empty list of transaction (Probably don't need)
        this.transactions = new ArrayList<>();

        /* adds the account to the owner and bank lists so account is added and UPDATED to
        UserInfo, Bank, and Account ArrayLists
        this in the add account parameter refers to "this" account being
        created by the constructor
         */
        owner.addAccount(this);
        bank.addAccount(this);

        // prints message describing all the account information.
        // Source - https://matthew-brett.github.io/teaching/string_formatting.html
//        System.out.printf(
//                "Your %s account has been created with %s bank "
//                        + "with %s balance and a UUID of %s.\n\n",
//                type, bankName, balance, uuid);

    }

    public String getType() {
        return type;
    }


    public String getUuid() {
        return uuid;
    }


    // REQUIRES: name have a length greater than 0, balance > 0
    // EFFECTS: constructs account with id, name and balance;
    // nextAccountId is the id of the next account to be constructed
    // NOTE: this constructor is only used to make an account from data stored in file
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String setName(String newName) {
        this.name = newName;
        return name;
    }

    /*
     * REQUIRES: amount >= 0
     * MODIFIES: this
     * EFFECTS: amount is added to balance and updated
     * 			balance is returned
     */
    public double deposit(double amount) {
        balance = balance + amount;
        return balance;
    }

    /*
     * REQUIRES: amount >= 0 and amount <= getBalance()
     * MODIFIES: this
     * EFFECTS: amount is withdrawn from account and updated
     * 		    balance is returned
     */
    public double withdraw(double amount) {

        balance = balance - amount;
        return balance;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(balance);
    }


    public String getAccountType() {
        return type;
    }

    public double getAccountBalance() {
        return balance;
    }

    public String getAccountUUId() {
        return uuid;
    }

    public UserInfo getUser() {
        return owner;
    }


    // Source - https://matthew-brett.github.io/teaching/string_formatting.html
    // REQUIRES: account
    // EFFECTS: prints a summary of the details of the account
    public String getSummaryLine() {
        // get the account's balance
        double balance = this.getBalance();
        return String.format("Account Number: %s , Balance: $%s ,"
                + " Type of Account: %s", this.uuid, balance, this.type);
    }

    // returns balance after transaction is completed
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance = t.getAmount() + balance;
        }
        return balance;
    }

    // Source - https://www.youtube.com/watch?v=k0BofouWX-o
    // REQUIRES: account
    // MODIFIES: this
    // EFFECTS: adds a transaction to the list of transactions of an Account
    public void addTransaction(double amount) {
        Transaction newTransaction = new Transaction(amount, this);
        this.transactions.add(newTransaction);
    }

    public double getSavedBalance() {
        return balance;
    }

}
