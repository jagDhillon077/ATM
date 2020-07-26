package ui;

import atm.Bank;
import atm.UserInfo;

import java.util.Scanner;
// ATM that creates an instance with a given account and scans for user inputs
// For all Variable within String method I used,
// Source - https://matthew-brett.github.io/teaching/string_formatting.html
// Got help with the code structure from
// Source - https://www.sanfoundry.com/java-program-display-atm-transaction/
// Source - https://www.youtube.com/watch?v=TUN_LhrIaqE
// Source - https://www.youtube.com/watch?v=k0BofouWX-o
// Source - https://www.youtube.com/watch?v=wQbEH4tVMJA


public class ATM {
    public static void run() {
        // creates a scanner to scan the inputs
        Scanner scanner = new Scanner(System.in);

        // creates a bank the ATM is associated with
        Bank bankName = new Bank("TD");

        // creates user with savings accounts
        UserInfo user =
                bankName.newUser("Jagmeet", "Dhillon", "jagmdhi", 111111);

        UserInfo tryUser;
        while (true) {
            // does not leave login prompt until successful login
            tryUser = ATM.loginScreenPrompt(bankName, scanner);

            // persistently stays in the ATM menu until successful login or quit
            // Scanner Source = https://www.w3schools.com/java/java_user_input.asp
            // Source # 2 - https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
            ATM.userMenu(tryUser, scanner);

        }
    }

    // Login Screen of ATM
    /* Login Ideas Source - https://www.tutorialspoint.com/how-can-we-create-a-login-form-in-java#
     :~:text=We%20can%20develop%20a%20login,and%20finally%20one%20submit%20button.
     */
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
            }

        } while (acceptedUser == null); //screen does not change until correct login attempt

        return acceptedUser;
    }

    // displays user menu with financing options
    public static void userMenu(UserInfo userInfo, Scanner scanner) {
        // show a summary of the users information, name, balance, which accounts
        userInfo.accountSummary();

        //initializing choice
        int choice;

        // initialize user menu
        do {
            System.out.printf("Welcome %s, please choose from the set of options\n",
                    userInfo.getFirstName());
            System.out.println("1) Withdraw");
            System.out.println("2) Deposit");
            System.out.println("3) Transfer between accounts");
            System.out.println("4) Exit");
            System.out.println();
            System.out.print("Enter Here: ");
            choice = scanner.nextInt();
            // prevents any mistyped numbers fromo being inputted
            if (1 > choice || choice > 4) {
                System.out.println("Please choose a choice between the numbers 1-4");
            }
        } while (choice < 1 || choice > 4);
        if (choice >= 1 || choice <= 4) {
            showUserMenu(userInfo, scanner, choice);
        }
    }

    // helper method for userMenu, reads input and excecutes task at hand
    public static void showUserMenu(UserInfo userInfo, Scanner scanner, int choice) {


        // retrieve info from choice 1-5
        if (choice == 1) {
            ATM.withdrawMoney(userInfo, scanner);
        } else if (choice == 2) {
            ATM.depositMoney(userInfo, scanner);
        } else if (choice == 3) {
            ATM.transferMoney(userInfo, scanner);
        }
        if (choice != 4) {
            // keeps the user display
            ATM.userMenu(userInfo, scanner);
        }
    }


    // Transfer money from one selected account to the other
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

    // helper method for transferMoney, checks if amount exceeds balance
    // and decides weather transfer should be made
    public static void readyTransfer(UserInfo userInfo, Scanner scanner, int fromAcct,
                                     int toAcct, double amount, double acctBal) {
        // transfer amount
        do {
            System.out.println("Ensure amount must not be greater than Account Balance");
            amount = scanner.nextDouble();
            if (amount > acctBal) {
                System.out.println("Amount must not be greater than Account Balance");
            }
        } while (amount > acctBal);

        makeTransfer(userInfo, scanner, fromAcct, toAcct, amount, acctBal);
    }

    // helper method for transferMoney, makes the transfer
    public static void makeTransfer(UserInfo userInfo, Scanner scanner, int fromAcct,
                                    int toAcct, double amount, double acctBal) {
        //transfer
        userInfo.addAcctTransaction(fromAcct, -1 * amount);
        userInfo.addAcctTransaction(toAcct, amount);
    }


    // withdraws money from selected user account
    public static void withdrawMoney(UserInfo userInfo, Scanner scanner) {

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
        userInfo.addAcctTransaction(fromAcct, -1 * amount);
    }

    // deposits money into the account
    public static void depositMoney(UserInfo userInfo, Scanner scanner) {

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
        userInfo.addAcctTransaction(toAcct, amount);

    }

}

