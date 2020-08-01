package ui;

import atm.Account;
import atm.Bank;
import atm.UserInfo;
import persistence.Writer;
import persistence.Reader;

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


public class ATM {
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private static Account checking;
    private static Account saving;
    private static Scanner input;
    private static Bank bankName;
    private static UserInfo user1;
    // creates user with savings accounts
    //UserInfo user =
    // bankName.newUser("Jagmeet",
    //   "Dhillon", "12", 1);
    private static String theBanksName;

    // runs ATM application
    public ATM() {
        ATM.run();
    }

    // MODIFIES: this
    // EFFECTS: creates an instance of an ATM that starts at the login screen and scans for
    // correct username/password combo
    public static void run() {
        // creates a scanner to scan the inputs
        Scanner scanner = new Scanner(System.in);

        // creates a bank the ATM is associated with
        Bank bankName = new Bank("TD");


        UserInfo tryUser = bankName.newUser("Jagmeet", "Dhillon", "1", 1);
        while (true) {
            // does not leave login prompt until successful login
            tryUser = ATM.loginScreenPrompt(bankName, scanner);
            //setAccountNames(tryUser.getUsername());
            scanner = new Scanner(System.in);
            loadAccounts();
            // persistently stays in the ATM menu until successful login or quit
            // Scanner Source = https://www.w3schools.com/java/java_user_input.asp
            // Source # 2 - https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

            ATM.userMenu(tryUser, scanner);

        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private static void loadAccounts() {
        try {
            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
            checking = accounts.get(0);
            saving = accounts.get(1);
        } catch (IOException e) {
            init();
        }
    }

    // EFFECTS: saves state of chequing and savings accounts to ACCOUNTS_FILE
    private static void saveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(checking);
            writer.write(saving);
            writer.close();
            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    private static void init() {
        // creates a bank the ATM is associated with
        Bank bankName = new Bank("TD");
        // creates user with savings accounts
        UserInfo user1 =
                bankName.newUser("Jagmeet",
                        "Dhillon", "12345", 111111);
        checking = new Account("Checking", 250, bankName, user1, "TD");
        saving = new Account("Savings", 500, bankName, user1, "TD");
        input = new Scanner(System.in);
    }


    /* Login Ideas Source - https://www.tutorialspoint.com/how-can-we-create-a-login-form-in-java#
     :~:text=We%20can%20develop%20a%20login,and%20finally%20one%20submit%20button.
     */
    // EFFECTS: Shows the Login screen of ATM
    public static UserInfo loginScreenPrompt(Bank bank, Scanner scanner) {
        //initializes the login variables
        // local variables Source -
        // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360000395439-Underlined-variables-
        String username;
        int password;
        UserInfo acceptedUser;

        // ask for username and password
        do {
            System.out.println("Welcome to TD Bank");
            System.out.println("Enter Username: ");
            username = scanner.nextLine();
            System.out.println("Enter Password: ");
            password = scanner.nextInt();
            // checks if userInfo in Data is similar to the returned login service details
            acceptedUser = bank.loginService(username, password);
            if (acceptedUser == null) {
                System.out.println("Please try again, your username and/or password is incorrect");
                run();
            }

        } while (acceptedUser == null); //screen does not change until correct login attempt
        return acceptedUser;
    }

    // REQUIRES: Correct login for user
    // EFFECTS: show summary of user information and asks the user what financial option to proceed with
    public static void userMenu(UserInfo userInfo, Scanner scanner) {
        // show a summary of the users information, name, balance, which accounts
        userInfo.accountSummary();

        //initializing choice
        int choice;


        // initialize user menu
        do {
            System.out.println("********************************************************"
                    + "******************************************************************");
            System.out.printf("Welcome %s, please choose from the set of options\n",
                    userInfo.getFirstName());
            System.out.println("1) Withdraw");
            System.out.println("2) Deposit");
            System.out.println("3) Transfer between accounts");
            System.out.println("4) Exit");
            System.out.println("5) Save accounts to file");
            System.out.println();
            System.out.print("Enter Here: ");
            System.out.println("\n********************************************************"
                    + "******************************************************************");
            choice = scanner.nextInt();
            // prevents any mistyped numbers from being inputted
            if (1 > choice || choice > 5) {
                System.out.println("Please choose a choice between the numbers 1-4");
            }
        } while (choice < 1 || choice > 5);
        showUserMenu(userInfo, scanner, choice);

    }

    // after the creation of the accounts, accounts usernames set
   // public static void setAccountNames(String setUsername) {
    //    init();
    //    checking.setName(setUsername);
   //     saving.setName(setUsername);
   // }


    // REQUIRES: choice to be between 1-4 Inclusive
    // EFFECTS: opens up a prompt depending on user selection (withdraw, deposit, transfer, quit)
    public static void showUserMenu(UserInfo userInfo, Scanner scanner, int choice) {


        // retrieve info from choice 1-5
        if (choice == 1) {
            ATM.withdrawMoney(userInfo, scanner);
        } else if (choice == 2) {
            ATM.depositMoney(userInfo, scanner);
        } else if (choice == 3) {
            ATM.transferMoney(userInfo, scanner);
        } else if (choice == 5) {
            ATM.saveAccounts();
        }
        if (choice != 4) {
            // keeps the user display
            ATM.userMenu(userInfo, scanner);
        } else if (choice == 4) {
            run();
        }
    }


    // Transfer money from one selected account to the other
    // REQUIRES: User and amount
    // MODIFIES: this
    // EFFECTS: selects a fromAccount, then a toAccount,
    // and asks how much user would like to transfer in between accounts
    public static void transferMoney(UserInfo userInfo, Scanner scanner) {

        // initialize the fields that would be to and account, from an account,
        // the amount transferred

        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        // get the account to transfer from
        do {
            System.out.println("Enter the number from (1-2) of the account\n to transfer from: ");
            fromAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= 2) {
                System.out.println("Invalid Account entered. Please try again.");
            }

        } while ((fromAcct < 0 || fromAcct >= 2));
        acctBal = userInfo.getAcctBalance(fromAcct);

        //get account to transfer too
        do {
            System.out.println("Enter the number from (1-2) of the account\n"
                    + "to transfer to: ");
            toAcct = scanner.nextInt() - 1;
            if (toAcct < 0 || toAcct >= 2) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= 2);
        // Initialize amount
        System.out.println("Enter the amount to transfer: ");
        amount = scanner.nextDouble();

        // ready up the transfer for processing
        readyTransfer(userInfo, scanner, fromAcct, toAcct, amount, acctBal);
    }


    // REQUIRES: transferMoney to call this helper
    // EFFECTS: makes sure the amount being transferred is less than or equal to the balance
    public static void readyTransfer(UserInfo userInfo, Scanner scanner, int fromAcct,
                                     int toAcct, double amount, double acctBal) {
        // transfer amount
        do {

            System.out.println("Retype amount to confirm,\n Ensure amount is not greater than account balance");
            amount = scanner.nextDouble();
            if (amount > acctBal) {
                System.out.println("Amount must not be greater than Account Balance");
            }
        } while (amount > acctBal);

        makeTransfer(userInfo, scanner, fromAcct, toAcct, amount, acctBal);
    }

    // REQUIRES: transferMoney to call this helper function
    // MODIFIES: this
    // EFFECTS: makes the transfer in between accounts
    public static void makeTransfer(UserInfo userInfo, Scanner scanner, int fromAcct,
                                    int toAcct, double amount, double acctBal) {
        //transfer
        userInfo.addAcctTransaction(fromAcct, -1 * amount);
        userInfo.addAcctTransaction(toAcct, amount);
    }


    // REQUIRES: fromAcct > amount and a call from the financial interface
    // MODIFIES: selected user account
    // EFFECTS: withdraws money from selected user account
    public static void withdrawMoney(UserInfo userInfo, Scanner scanner) {
        Account selected = selectAccount(scanner);
        int fromAcct;
        int toAcct;
        double acctBal;
        double amount;


        // get the account to withdraw from
        do {
            System.out.println("Enter the number from (1-2) of the account\n"
                    + "to withdraw from: ");
            fromAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= 2) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= 2);
        acctBal = userInfo.getAcctBalance(fromAcct);

        // transfer amount
        do {
            System.out.println("Enter the amount to withdraw: ");
            amount = scanner.nextDouble();
            if (amount > acctBal) {
                System.out.println("Amount must not be greater than Account Balance");
            }
        } while (amount > acctBal);

        scanner.nextLine();

        // make withdrawal
        userInfo.addAcctTransaction(fromAcct, -1 * amount);  // withdraw that occurs in the console
        selected.withdraw(amount); // withdraw that occurs behind the scenes

    }

    // REQUIRES: amount > 0
    // MODIFIES: account being deposited into
    // EFFECTS: deposits amount into the bank account
    public static void depositMoney(UserInfo userInfo, Scanner scanner) {
        Account selected = selectAccount(scanner);
        int toAcct;
        double acctBal;
        double amount;


        // get the account to deposit to
        do {
            System.out.println("Enter the number from (1-2) of the account\n"
                    + "to deposit to: ");
            toAcct = scanner.nextInt() - 1;
            if (toAcct < 0 || toAcct >= 2) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= 2);
        acctBal = userInfo.getAcctBalance(toAcct);

        if (toAcct == 1) {
            selected.setName("Savings");
        } else {
            selected.setName("Checking");
        }

        // transfer amount
        do {
            System.out.println("Enter the amount to transfer: ");
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0");
            }
        } while (amount < 0);

        scanner.nextLine();

        // make deposit
        userInfo.addAcctTransaction(toAcct, amount); // deposit that occurs in the console
        selected.deposit(amount); // deposit behind the scenes for the save file
    }


    //===========================================
    // EFFECTS: prompts user to select chequing or savings account and returns it
    private static Account selectAccount(Scanner scanner) {
        int selection = 0;  // force entry into loop

        while (!(selection == 1 || selection == 2)) {
            System.out.println("1 for checking");
            System.out.println("2 for saving");
            selection = scanner.nextInt();
        }

        if (selection == 1) {
            return checking;
        } else {
            return saving;
        }
    }

}

