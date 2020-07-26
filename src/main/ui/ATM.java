package ui;

import atm.Account;
import atm.Bank;
import atm.UserInfo;

import java.util.Scanner;

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
            ATM.showUserMenu(tryUser, scanner);

        }
    }


    public static UserInfo loginScreenPrompt(Bank bank, Scanner scanner) {
        //initializes the Login Screen
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

            acceptedUser = bank.loginService(username, password);
            if (acceptedUser == null) {
                System.out.println("Please try again, your username and/or password is incorrect");
            }

        } while (acceptedUser == null); //screen does not change until correct login attempt

        return acceptedUser;
    }

    public static void showUserMenu(UserInfo userInfo, Scanner scanner) {
        // show a summary of the users information, name, balance, which accounts
        userInfo.accountSummary();

        //initializing choice
        int choice;

        // initialize user menu
        do {
            System.out.printf("Welcome %s, please choose from the set of options",
                    userInfo.getFirstName());
            System.out.println("1) Show History");
            System.out.println("2) Withdraw");
            System.out.println("3) Deposit");
            System.out.println("5) Transfer between accounts");
            System.out.println("4) Exit");
            System.out.println();
            System.out.print("Enter Here: ");
            choice = scanner.nextInt();

            if (1 > choice || choice > 5) {
                System.out.println("Please choose a choice between the numbers 1-5");
            }
        } while (choice < 1 || choice > 5);

        // retrieve info from choice 1-5
        if (choice == 1) {
            ATM.transactionhistory(userInfo, scanner);
        } else if (choice == 2) {
            ATM.withdrawMoney(userInfo, scanner);
        } else if (choice == 3) {
            ATM.depositMoney(userInfo, scanner);
        } else if (choice == 4) {
            ATM.transferMoney(userInfo, scanner);
        }
        // keeps the user display
        if (choice != 5) {
            ATM.showUserMenu(userInfo, scanner);
        }
    }

    /*
    Displays transaction history of the account
     */

    public static void transactionhistory(UserInfo userInfo, Scanner scanner) {
        int theAcct;
        //select an account to look at its history
        do {
            System.out.printf("Enter the number (1-%d) of the account/n"
                            + "whose transaction you want to see: ",
                    userInfo.numAccounts());
            theAcct = scanner.nextInt() - 1;
            if (theAcct < 0 || theAcct >= userInfo.numAccounts()) {
                System.out.println("Invalid Account entered. Please try again.");
            }

        } while (theAcct < 0 || theAcct >= userInfo.numAccounts());

        //print history
        userInfo.printAccountHistory(theAcct);
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
            System.out.printf("Enter the number from (1-%d) of the account\n"
                    + "to transfer from: ");
            fromAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= userInfo.numAccounts()) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while ((fromAcct < 0 || fromAcct >= userInfo.numAccounts()));
        acctBal = userInfo.getAcctBalance(fromAcct);

        //get account to transfer too
        do {
            System.out.printf("Enter the number from (1-%d) of the account\n"
                    + "to transfer to: ");
            toAcct = scanner.nextInt() - 1;
            if (toAcct < 0 || toAcct >= userInfo.numAccounts()) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while ((toAcct < 0 || toAcct >= userInfo.numAccounts()));

        // transfer amount
        do {
            System.out.printf("Enter the amount to transfer: ");
            amount = scanner.nextDouble();
            if (amount > acctBal) {
                System.out.println("Amount must not be greater than Account Balance");
            }
        } while (amount > acctBal);

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


        // get the account to transfer from
        do {
            System.out.printf("Enter the number from (1-%d) of the account\n"
                    + "to transfer from: ");
            fromAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= userInfo.numAccounts()) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while ((fromAcct < 0 || fromAcct >= userInfo.numAccounts()));
        acctBal = userInfo.getAcctBalance(fromAcct);

        // transfer amount
        do {
            System.out.printf("Enter the amount to transfer: ");
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


        // get the account to transfer from
        do {
            System.out.printf("Enter the number from (1-%d) of the account\n"
                    + "to transfer from: ");
            toAcct = scanner.nextInt() - 1;
            if (toAcct < 0 || toAcct >= userInfo.numAccounts()) {
                System.out.println("Invalid Account entered. Please try again.");
            }
        } while ((toAcct < 0 || toAcct >= userInfo.numAccounts()));
        acctBal = userInfo.getAcctBalance(toAcct);

        // transfer amount
        do {
            System.out.printf("Enter the amount to transfer: ");
            amount = scanner.nextDouble();
            if (amount > acctBal) {
                System.out.println("Amount must not be greater than Account Balance");
            }
        } while (amount > acctBal);

        scanner.nextLine();

        // make withdrawal
        userInfo.addAcctTransaction(toAcct, amount);

    }

}

