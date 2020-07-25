package atm;


import java.util.ArrayList;
// Account that can be accessed and modified by the account owner.

public class Account {

    // What type of account is this, ie (Chequing, Saving, Emergency...)
    private String type;
    // Is a universal way to refer to this account
    private int accountNumber;
    // Balance in account
    private double balance;
    // Provides the UserInfo of who owns this certain account
    private UserInfo owner;
    // List that remembers the transactions made to and from this account
    private ArrayList<Transaction> transactions;


}
