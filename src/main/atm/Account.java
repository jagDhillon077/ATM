package atm;


import java.util.ArrayList;
// Account that can be accessed and modified by the account owner.
// ideas on how to create a bank and account
// Source - https://www.youtube.com/watch?v=mp1_F7lfmNE

public class Account {

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

    /*
    Creates new Account with Account type, balance, Bank the account is associated with
    and the account owner
     */
    public Account(String type, int balance, Bank bank, UserInfo owner, String bankName) {
        // sets account type and the owner
        this.type = type;
        this.owner = owner;
        this.balance = balance;

        // gets UUID for account
        this.uuid = bank.getNewAccountUUId();

        // initialize an empty list of transaction
        this.transactions = new ArrayList<>();

        /* adds the account to the owner and bank lists so account is added and UPDATED to
        UserInfo, Bank, and Account ArrayLists
        this in the add account parameter refers to "this" account being
        created by the constructor
         */
        owner.addAccount(this);
        bank.addAccount(this);

        // prints message describing all the account information.
        System.out.printf(
                "Your %s account has been created with %s bank with %s balance and a UUID of %s.\n\n",
                type, bankName, balance, uuid);

    }


    // Get a summary line for the account
    // Source - https://matthew-brett.github.io/teaching/string_formatting.html
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


    public void addTransaction(double amount) {
        // create transaction object and add to list
        Transaction newTransaction = new Transaction(amount, this);
        this.transactions.add(newTransaction);
    }


}
