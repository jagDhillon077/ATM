package ui;

import atm.Account;
import atm.Bank;
import atm.UserInfo;
import persistence.Writer;
import persistence.Reader;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import java.util.Scanner;
// ATM that creates an instance with a given account and scans for user inputs
// For all Variable within String method I used,
// Source - https://matthew-brett.github.io/teaching/string_formatting.html
// Got ideas/help with the code structure from
// Source - https://www.sanfoundry.com/java-program-display-atm-transaction/
// Source - https://www.youtube.com/watch?v=TUN_LhrIaqE
// Source - https://www.youtube.com/watch?v=k0BofouWX-o
// Source - https://www.youtube.com/watch?v=wQbEH4tVMJA
// Source - https://www.youtube.com/watch?v=DiA504r_L7k
// Source - https://www.youtube.com/watch?v=0nDYk8sV_jQ
// Source - TellerApp code and logic is integrated to this
// program as TellerApp perfectly fit the needs of this program


public class ATM {
//    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
//    private static Account checking;
//    private static Account saving;
//    private static final Account newChecking = new Account("Checking", 0.0);
//    private static final Account newSaving = new Account("Savings", 0.0);
//
//    // runs ATM application
//    public ATM() {
//        ATM.run();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates an instance of an ATM that starts at the login screen and scans for
//    // correct username/password combo
//    public static void run() {
//        // creates a scanner to scan the inputs
//        Scanner scanner = new Scanner(System.in);
//
//        // creates a bank the ATM is associated with
//        Bank bankName = new Bank("TD");
//
//
//        UserInfo tryUser = bankName.newUser("Jagmeet", "Dhillon", "1", 1);
//        while (true) {
//            int command;
//            // does not leave login prompt until successful login
//            tryUser = ATM.loginScreenPrompt(bankName, scanner);
//            //setAccountNames(tryUser.getUsername());
//            scanner = new Scanner(System.in);
//            do {
//                System.out.println("What would you like to do?\n");
//                System.out.println("Press 1 to Load Saved Accounts?");
//                System.out.println("Press 2 to Start with New Accounts\n"
//                        + "WARNING: Option 2 Overwrites previously saved accounts");
//                command = scanner.nextInt();
//                if (command < 1 || command > 2) {
//                    System.out.println("Please enter a Valid Command [1-2]");
//                }
//
//            } while (command < 1 || command > 2);
//            runAccountCommand(command, tryUser, scanner);
//        }
//
//    }
//
//    // EFFECTS: If 1 is selected, loads the accounts saved into file,
//    // Selection 2 initializes new accounts to the user
//    public static void runAccountCommand(int command, UserInfo tryUser, Scanner scanner) {
//        if (command == 1) {
//            loadAccounts();
//            // persistently stays in the ATM menu until successful login or quit
//            // Scanner Source = https://www.w3schools.com/java/java_user_input.asp
//            // Source # 2 - https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
//
//            ATM.loadedUserMenu(tryUser, scanner);
//        } else if (command == 2) {
//            ATM.newUserMenu(tryUser, scanner);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
//    // otherwise initializes accounts with default values
//    public static void loadAccounts() {
//        try {
//            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
//            checking = accounts.get(0);
//            saving = accounts.get(1);
//        } catch (IOException e) {
//            //////init();
//        }
//    }
//
//
//
//    // Initializes the new accounts made
//    private static void init() {
//        // creates a bank the ATM is associated with
//        Bank bankName = new Bank("TD");
//        // creates user with savings accounts
//        UserInfo user1 =
//                bankName.newUser("Jagmeet",
//                        "Dhillon", "12345", 111111);
//        checking = new Account("Checking", 250, bankName, user1, "TD");
//        saving = new Account("Savings", 500, bankName, user1, "TD");
//        Scanner input = new Scanner(System.in);
//    }
//
//
//    /* Login Ideas Source - https://www.tutorialspoint.com/how-can-we-create-a-login-form-in-java#
//     :~:text=We%20can%20develop%20a%20login,and%20finally%20one%20submit%20button.
//     */
//    // EFFECTS: Shows the Login screen of ATM
//    public static UserInfo loginScreenPrompt(Bank bank, Scanner scanner) {
//        //initializes the login variables
//        // local variables Source -
//        // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360000395439-Underlined-variables-
//        String username;
//        int password;
//        UserInfo acceptedUser;
//
//        // ask for username and password
//        do {
//            System.out.println("Welcome to TD Bank");
//            System.out.println("Enter Username: ");
//            username = scanner.nextLine();
//            System.out.println("Enter Password: ");
//            password = scanner.nextInt();
//            // checks if userInfo in Data is similar to the returned login service details
//            acceptedUser = bank.loginService(username, password);
//            if (acceptedUser == null) {
//                System.out.println("Please try again, your username and/or password is incorrect");
//                run();
//            }
//
//        } while (acceptedUser == null); //screen does not change until correct login attempt
//        return acceptedUser;
//    }
//
//    // REQUIRES: Correct login for user
//    // EFFECTS: show summary of saved user information and asks the user what financial option to proceed with
//    public static void loadedUserMenu(UserInfo userInfo, Scanner scanner) {
//        // show a summary of the users information, name, balance, which accounts
//        userInfo.accountSummary();
//
//        //initializing choice
//        int choice;
//
//
//        // initialize user menu
//        do {
//            System.out.println("********************************************************"
//                    + "******************************************************************");
//            System.out.println("Welcome, please choose from the set of options\n");
//            System.out.printf("Balance of %s's CHECKING Account: %s",
//            userInfo.getFirstName(), checking.getBalancee());
//            System.out.printf("\nBalance of %s's SAVINGS Account: %s\n",
//            userInfo.getFirstName(), saving.getBalancee());
//            System.out.println("\n1) Withdraw");
//            System.out.println("2) Deposit");
//            System.out.println("3) Transfer between accounts");
//            System.out.println("4) Exit");
//            System.out.println("5) Save accounts to file");
//            System.out.println();
//            System.out.print("Enter Here: ");
//            choice = scanner.nextInt();
//            // prevents any mistyped numbers from being inputted
//            if (1 > choice || choice > 5) {
//                System.out.println("Please choose a choice between the numbers 1-4");
//            }
//        } while (choice < 1 || choice > 5);
//        showUserMenu(userInfo, scanner, choice); //
//
//    }
//
//    // REQUIRES: choice to be between 1-4 Inclusive
//    // EFFECTS: opens up a prompt depending on user selection (withdraw, deposit, transfer, quit)
//    public static void showUserMenu(UserInfo userInfo, Scanner scanner, int choice) {
//
//
//        // retrieve info from choice 1-5
//        if (choice == 1) {
//            ATM.withdrawMoney(userInfo, scanner);
//        } else if (choice == 2) {
//            ATM.depositMoney(userInfo, scanner);
//        } else if (choice == 3) {
//            ATM.transferMoney(userInfo, scanner);
//        } else if (choice == 5) {
//            //ATM.saveAccounts();
//        }
//        if (choice != 4) {
//            // keeps the user display
//            ATM.loadedUserMenu(userInfo, scanner);
//        } else {
//            System.exit(0);
//        }
//    }
//
//    // Transfer money from one selected account to the other
//    // REQUIRES: User and amount
//    // MODIFIES: this
//    // EFFECTS: selects a fromAccount, then a toAccount,
//    // and asks how much user would like to transfer in between accounts
//    public static void transferMoney(UserInfo userInfo, Scanner scanner) {
//
//        // initialize the fields that would be to and account, from an account,
//        // the amount transferred
//
//        int fromAcct;
//        int toAcct;
//        double amount;
//        double acctBal;
//
//        // get the account to transfer from
//        do {
//            System.out.println("Enter the number from (1-2) of the account\n to transfer from: ");
//            fromAcct = scanner.nextInt() - 1;
//            if (fromAcct < 0 || fromAcct >= 2) {
//                System.out.println("Invalid Account entered. Please try again.");
//            }
//
//
//        } while ((fromAcct < 0 || fromAcct >= 2));
//        acctBal = userInfo.getAcctBalance(fromAcct);
//        Account source = selectAccount(scanner);
//
//        //get account to transfer too
//        do {
//            System.out.println("Enter the number from (1-2) of the account\n"
//                    + "to transfer to: ");
//            toAcct = scanner.nextInt() - 1;
//
//        } while (toAcct < 0 || toAcct >= 2);
//        Account destination = selectAccount(scanner);
//        // Initialize amount
//        System.out.println("Enter the amount to transfer: ");
//        amount = scanner.nextDouble();
//
//        // ready up the transfer for processing
//        readyTransfer(userInfo, scanner, fromAcct, toAcct, acctBal, source, destination);
//    }
//
//    // REQUIRES: transferMoney to call this helper
//    // EFFECTS: makes sure the amount being transferred is less than or equal to the balance
//    public static void readyTransfer(UserInfo userInfo, Scanner scanner, int fromAcct,
//                                     int toAcct, double acctBal, Account source, Account destination) {
//
//        // transfer amount
//        System.out.println("Retype amount to confirm,\n Ensure amount is not greater than account balance");
//        double amount = scanner.nextDouble();
//
//
//        makeTransfer(userInfo, scanner, fromAcct, toAcct, amount, acctBal, source, destination);
//    }
//
//    // REQUIRES: transferMoney to call this helper function
//    // MODIFIES: this
//    // EFFECTS: makes the transfer in between accounts
//    public static void makeTransfer(UserInfo userInfo, Scanner scanner, int fromAcct,
//                                    int toAcct, double amount, double acctBal, Account source, Account destination) {
//        //transfer
//        userInfo.addAcctTransaction(fromAcct, -1 * amount);
//        userInfo.addAcctTransaction(toAcct, amount);
//        source.withdraw(amount); // withdraw that occurs behind the scenes
//        // CHANGED THIS...DELETED ACCOUNT AND SCANNER PARAMETER
//        destination.deposit(amount); // deposit behind the scenes for the save file
//    }
//
//    // REQUIRES: fromAcct > amount and a call from the financial interface
//    // MODIFIES: selected user account
//    // EFFECTS: withdraws money from selected user account
//    public static void withdrawMoney(UserInfo userInfo, Scanner scanner) {
//
//        int fromAcct;
//        double acctBal;
//        double amount;
//
//
//        // get the account to withdraw from
//        do {
//            System.out.println("Enter the number from (1-2) of the account\n"
//                    + "to withdraw from: ");
//            fromAcct = scanner.nextInt() - 1;
//            if (fromAcct < 0 || fromAcct >= 2) {
//                System.out.println("Invalid Account entered. Please try again.");
//            }
//        } while (fromAcct < 0 || fromAcct >= 2);
//        acctBal = userInfo.getAcctBalance(fromAcct);
//
//        Account selected = selectAccount(scanner);
//
//        // transfer amount
//        System.out.println("Enter the amount to withdraw: ");
//        amount = scanner.nextDouble();
//
//
//        // make withdrawal
//        userInfo.addAcctTransaction(fromAcct, -1 * amount);  // withdraw that occurs in the console
//        selected.withdraw(amount); // withdraw that occurs behind the scenes
//        // CHANGED THIS...DELETED ACCOUNT AND SCANNER PARAMETER
//
//    }
//
//    // REQUIRES: amount > 0
//    // MODIFIES: account being deposited into
//    // EFFECTS: deposits amount into the bank account
//    public static void depositMoney(UserInfo userInfo, Scanner scanner) {
//        int toAcct;
//        double acctBal;
//        // get the account to deposit to
//        do {
//            System.out.println("1 for checking");
//            System.out.println("2 for saving");
//            toAcct = scanner.nextInt() - 1;
//            if (toAcct < 0 || toAcct >= 2) {
//                System.out.println("Invalid Account entered. Please try again.");
//            }
//        } while (toAcct < 0 || toAcct >= 2);
//        acctBal = userInfo.getAcctBalance(toAcct);
//
//        depositToSelectedAccount(toAcct, userInfo, scanner);
//    }
//
//    public static void depositToSelectedAccount(int toAcct, UserInfo userInfo, Scanner scanner) {
//        Account selected = selectAccount(scanner);
//        if (toAcct == 1) {
//            selected.setName("Savings");
//        } else {
//            selected.setName("Checking");
//        }
//
//        // transfer amount
//        double amount;
//        do {
//            System.out.println("Enter the amount to transfer: ");
//            amount = scanner.nextDouble();
//            if (amount < 0) {
//                System.out.println("Amount must be greater than 0");
//            }
//        } while (amount < 0);
//
//        scanner.nextLine();
//
//        // make deposit
//        userInfo.addAcctTransaction(toAcct, amount); // deposit that occurs in the console
//        selected.deposit(amount); // deposit behind the scenes for the save file
//    }
//
//    // EFFECTS: prompts user to select chequing or savings account and returns it
//    private static Account selectAccount(Scanner scanner) {
//        int selection = 0;  // force entry into loop
//
//        while (!(selection == 1 || selection == 2)) {
//            System.out.println("Confirm entry of the account [1-2]\n"
//                    + "to make action on: ");
//            selection = scanner.nextInt();
//        }
//
//        if (selection == 1) {
//            return checking;
//        } else {
//            return saving;
//        }
//    }
//    //===========================================================================================
//    //===========================================================================================
//    //======================== SEPARATION OF NEW AND LOADED ACCOUNT METHODS======================
//    //===========================================================================================
//    //===========================================================================================
//
//    // REQUIRES: Correct login for user
//    // EFFECTS: show summary of NEW user information and asks the user what financial option to proceed with
//    // Does not include the balance of the accounts, as it has just been created, and today's
//    // summary is able to show the Account values
//    public static void newUserMenu(UserInfo userInfo, Scanner scanner) {
//        // show a summary of the users information, name, balance, which accounts
//        userInfo.accountSummary();
//
//        //initializing choice
//        int choice;
//
//
//        // initialize user menu
//        do {
//            System.out.println("********************************************************"
//                    + "******************************************************************");
//            System.out.println("Welcome, please choose from the set of options\n");
//
//            System.out.println("\n1) Withdraw");
//            System.out.println("2) Deposit");
//            System.out.println("3) Transfer between accounts");
//            System.out.println("4) Exit");
//            System.out.println("5) Save accounts to file");
//            System.out.println();
//            System.out.print("Enter Here: ");
//            choice = scanner.nextInt();
//            // prevents any mistyped numbers from being inputted
//            if (1 > choice || choice > 5) {
//                System.out.println("Please choose a choice between the numbers 1-4");
//            }
//        } while (choice < 1 || choice > 5);
//        showNewUserMenu(userInfo, scanner, choice);
//
//    }
//
//    // REQUIRES: choice to be between 1-4 Inclusive
//    // EFFECTS: opens up a prompt depending on user selection for the new user accounts
//    // (withdraw, deposit, transfer, quit)
//    public static void showNewUserMenu(UserInfo userInfo, Scanner scanner, int choice) {
//
//
//        // retrieve info from choice 1-5
//        if (choice == 1) {
//            ATM.withdrawNewMoney(userInfo, scanner);
//        } else if (choice == 2) {
//            ATM.newDepositMoney(userInfo, scanner);
//        } else if (choice == 3) {
//            ATM.newTransferMoney(userInfo, scanner);
//        } else if (choice == 5) {
//            ATM.saveNewAccounts();
//        }
//        if (choice != 4) {
//            // keeps the user display
//            ATM.newUserMenu(userInfo, scanner);
//        } else {
//            System.exit(0);
//        }
//    }
//
//    // REQUIRES: fromAcct > amount and a call from the financial interface
//    // MODIFIES: selected NEW user account
//    // EFFECTS: withdraws money from selected user account
//    public static void withdrawNewMoney(UserInfo userInfo, Scanner scanner) {
//
//        int fromAcct;
//        int toAcct;
//        double acctBal;
//        double amount;
//
//
//        // get the account to withdraw from
//        do {
//            System.out.println("Enter the number from (1-2) of the account\n"
//                    + "to withdraw from: ");
//            fromAcct = scanner.nextInt() - 1;
//            if (fromAcct < 0 || fromAcct >= 2) {
//                System.out.println("Invalid Account entered. Please try again.");
//            }
//        } while (fromAcct < 0 || fromAcct >= 2);
//        acctBal = userInfo.getAcctBalance(fromAcct);
//
//        Account selected = selectNewAccount(scanner);
//
//        // transfer amount
//        System.out.println("Enter the amount to withdraw: ");
//        amount = scanner.nextDouble();
//
//
//        // make withdrawal
//        userInfo.addAcctTransaction(fromAcct, -1 * amount);  // withdraw that occurs in the console
//        selected.withdraw(amount); // withdraw that occurs behind the scenes
//        // CHANGED THIS...DELETED ACCOUNT AND SCANNER PARAMETER
//
//    }
//
//    // REQUIRES: amount > 0
//    // MODIFIES: NEW account being deposited into
//    // EFFECTS: deposits amount into the bank account
//    public static void newDepositMoney(UserInfo userInfo, Scanner scanner) {
//        int toAcct;
//        double acctBal;
//        double amount;
//        // get the account to deposit to
//        do {
//            System.out.println("1 for checking");
//            System.out.println("2 for saving");
//            toAcct = scanner.nextInt() - 1;
//            if (toAcct < 0 || toAcct >= 2) {
//                System.out.println("Invalid Account entered. Please try again.");
//            }
//        } while (toAcct < 0 || toAcct >= 2);
//        acctBal = userInfo.getAcctBalance(toAcct);
//
//        depositToNewSelectedAccount(toAcct, userInfo, scanner);
//    }
//
//    // Transfer money from one selected account to the other
//    // REQUIRES: User and amount
//    // MODIFIES: this
//    // EFFECTS: selects a NEW source account, then a NEW destination account,
//    // and asks how much user would like to transfer in between accounts
//    public static void newTransferMoney(UserInfo userInfo, Scanner scanner) {
//
//        // initialize the fields that would be to and account, from an account,
//        // the amount transferred
//
//        int fromAcct;
//        int toAcct;
//        double amount;
//        double acctBal;
//
//        // get the account to transfer from
//        do {
//            System.out.println("Enter the number from (1-2) of the account\n to transfer from: ");
//            fromAcct = scanner.nextInt() - 1;
//            if (fromAcct < 0 || fromAcct >= 2) {
//                System.out.println("Invalid Account entered. Please try again.");
//            }
//
//
//        } while ((fromAcct < 0 || fromAcct >= 2));
//        acctBal = userInfo.getAcctBalance(fromAcct);
//        Account source = selectNewAccount(scanner);
//
//        //get account to transfer too
//        do {
//            System.out.println("Enter the number from (1-2) of the account\n"
//                    + "to transfer to: ");
//            toAcct = scanner.nextInt() - 1;
//
//        } while (toAcct < 0 || toAcct >= 2);
//        Account destination = selectNewAccount(scanner);
//        // Initialize amount
//        System.out.println("Enter the amount to transfer: ");
//        amount = scanner.nextDouble();
//
//        // ready up the transfer for processing
//        readyTransfer(userInfo, scanner, fromAcct, toAcct, acctBal, source, destination);
//    }
//
//    // Helper for depositNewAccount
//    // selects the NEW account being deposited into
//    public static void depositToNewSelectedAccount(int toAcct, UserInfo userInfo, Scanner scanner) {
//        Account selected = selectNewAccount(scanner);
//        if (toAcct == 1) {
//            selected.setName("Savings");
//        } else {
//            selected.setName("Checking");
//        }
//
//        // transfer amount
//        double amount;
//        do {
//            System.out.println("Enter the amount to transfer: ");
//            amount = scanner.nextDouble();
//            if (amount < 0) {
//                System.out.println("Amount must be greater than 0");
//            }
//        } while (amount < 0);
//
//        scanner.nextLine();
//
//        // make deposit
//        userInfo.addAcctTransaction(toAcct, amount); // deposit that occurs in the console
//        selected.deposit(amount); // deposit behind the scenes for the save file
//    }
//
//
//    // EFFECTS: prompts user to select NEW chequing or NEW savings account and returns it
//    private static Account selectNewAccount(Scanner scanner) {
//        int selection = 0;  // force entry into loop
//
//        while (!(selection == 1 || selection == 2)) {
//            System.out.println("Confirm entry of the account [1-2]\n"
//                    + "to make action on: ");
//            selection = scanner.nextInt();
//        }
//
//        if (selection == 1) {
//            return newChecking;
//        } else {
//            return newSaving;
//        }
//    }
//
//    // EFFECTS: saves state of NEW chequing and NEW savings accounts to ACCOUNTS_FILE
//    private static void saveNewAccounts() {
//        try {
//            Writer writer = new Writer(new File(ACCOUNTS_FILE));
//            writer.write(newChecking);
//            writer.write(newSaving);
//            writer.close();
//            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            // this is due to a programming error
//        }
//    }
}

