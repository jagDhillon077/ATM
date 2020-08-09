package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JPanel;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import atm.Account;
import atm.Bank;
import atm.UserInfo;
import persistence.Reader;
import persistence.Writer;
import sound.Sound;

public class GUI extends JFrame implements ActionListener {
    //==========================================================================================
    //==================================GUI Fields==============================================
    //==========================================================================================
    private static JPasswordField passwordInput;
    private static ImageIcon bankSymbol;
    private static final Color LIGHT_BLUE = new Color(51, 204, 255);
    private static JLabel usernameLabel;
    private static JLabel passwordLabel;
    private static JLabel welcomeLabel;
    private static JLabel selectOptionsLabel;
    private static JLabel enterAmountLabel;
    private static JLabel loginPassOrFail;
    private static JLabel checkingAccountExplanationLabel;
    private static JLabel selectAccountLabelDeposit;
    private static JLabel savingAccountExplanationLabel;
    private static JLabel selectAccountLabelTransfer;
    private static JLabel transferAccountExplanationLabel;
    private static JTextField userInput;
    private static JTextField amountEnteredDeposit;
    private static JTextField amountEnteredWithdraw;
    private static JTextField amountEnteredTransfer;
    private static JTextArea textAreaTopHalf;
    private static JTextArea textAreaBottomHalf;
    private static JTextArea loginTextArea;
    private static JLabel selectAccountLabelWithdraw;
    private static JButton button;
    private static JButton confirmDepositButtonSavings;
    private static JButton savingsAccountButtonDeposit;
    private static JButton checkingAccountButtonDeposit;
    private static JButton withdrawButton;
    private static JButton depositButton;
    private static JButton transferButton;
    private static JButton saveAccountsButton;
    private static JButton exitButton;
    private static JButton confirmDepositButtonChecking;
    private static JButton savingsAccountButtonWithdraw;
    private static JButton checkingAccountButtonWithdraw;
    private static JButton confirmWithdrawButtonSavings;
    private static JButton confirmWithdrawButtonChecking;
    private static JButton savingsToCheckingAccountButtonTransfer;
    private static JButton checkingToSavingsAccountButtonTransfer;
    private static JButton confirmSavingsToCheckingAccountButtonTransfer;
    private static JButton confirmCheckingToSavingsAccountButtonTransfer;
    //==========================================================================================
    //==================================ATM Fields==============================================
    //==========================================================================================
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private static Account checking;
    private static Account saving;
    private static final Account newChecking = new Account("Checking", 0.0);
    private static final Account newSaving = new Account("Savings", 0.0);
    private static Bank bankName = new Bank("TD");
    private static UserInfo tryUser = bankName.newUser("Jagmeet", "Dhillon", "1", 1);
    //private static Account

    public static void main(String[] args) {
        // TODO Grader Instructions
        loginScreenGuiOnStart();
    }

    private static void loginScreenGuiOnStart() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(415, 275));
        panel.setBackground(LIGHT_BLUE);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(null); // CHANGE THIS TO GRID???
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setResizable(true);
        welcome(panel);
        loginTextArea = new JTextArea();
        loginTextArea.setBounds(10, 130, 375, 100);
        panel.add(loginTextArea);
        loginTextArea.append(String.format("Welcome %s %s, your account has been created with \nUsername: %s"
                        + " \nPin: %s \n\n",
                tryUser.getFirstName(), tryUser.getLastName(), tryUser.getPassword(), tryUser.getUsername()));
        enterAmountLabel = new JLabel("Please Enter Amount");
    }

    private static void welcome(JPanel panel) {
        // unmodifiable "Welcome Label"
        welcomeLabel = new JLabel("Welcome to ATM #0627");
        welcomeLabel.setBounds(120, 10, 150, 25);
        panel.add(welcomeLabel);
        // unmodifiable "Username" text
        usernameLabel = new JLabel("Enter Username: ");
        usernameLabel.setBounds(10, 40, 100, 25);
        panel.add(usernameLabel);
        // unmodifiable "Password" text
        passwordLabel = new JLabel("Enter Password: ");
        passwordLabel.setBounds(10, 70, 100, 25);
        panel.add(passwordLabel);
        // username input field
        userInput = new JTextField(20);
        userInput.setBounds(115, 40, 165, 25);
        panel.add(userInput);
        // password input field
        passwordInput = new JPasswordField(20);
        passwordInput.setBounds(115, 70, 165, 25);
        panel.add(passwordInput);
        // SPECIFIC PASSWORD FIELD FOR HIDDEN PASSWORDS
        // guiLoginScreenHelper prevents method from being 25+ lines
        // login attempt button
        button = new JButton("Login");
        button.setBounds(10, 100, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);
        // login pass or fail notifier
        loginPassOrFail = new JLabel("");
        loginPassOrFail.setBounds(100, 100, 300, 25);
        panel.add(loginPassOrFail);
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

    // Initializes the new accounts made
    private static void init() {
        // creates a bank the ATM is associated with
        Bank bankName = new Bank("TD");
        // creates user with savings accounts
        UserInfo user1 =
                bankName.newUser("Jagmeet",
                        "Dhillon", "12345", 111111);
        checking = new Account("Checking", 250, bankName, user1, "TD");
        saving = new Account("Savings", 500, bankName, user1, "TD");
        Scanner input = new Scanner(System.in);
    }

    public void actionPerformedHelper(ActionEvent e) {
        if (e.getSource() == button) {
            loginButtonAction();
        } else if (e.getSource() == withdrawButton) {
            withdrawButtonAction();
        } else if (e.getSource() == depositButton) {
            depositButtonAction();
        } else if (e.getSource() == transferButton) {
            transferButtonAction();
        } else if (e.getSource() == saveAccountsButton) {
            saveAccountsButtonAction();
        } else if (e.getSource() == exitButton) {
            exitButtonAction();
        } else if (e.getSource() == savingsAccountButtonDeposit) {
            savingsAccountButtonDepositAction();
        } else if (e.getSource() == checkingAccountButtonDeposit) {
            checkingAccountButtonDepositAction();
        } else if (e.getSource() == savingsAccountButtonWithdraw) {
            savingsAccountButtonWithdrawAction();
        } else if (e.getSource() == checkingAccountButtonWithdraw) {
            checkingAccountButtonWithdrawAction();
        } else if (e.getSource() == confirmDepositButtonSavings) {
            confirmDepositButtonSavingsAction();
        }
    }

    public void actionPerformed(ActionEvent e) {
        // creates a bank the ATM is associated with

        if ((e.getSource() == button) || (e.getSource() == withdrawButton) || (e.getSource() == depositButton)
                || (e.getSource() == transferButton) || (e.getSource() == saveAccountsButton)
                || (e.getSource() == exitButton) || (e.getSource() == savingsAccountButtonDeposit)
                || (e.getSource() == checkingAccountButtonDeposit) || (e.getSource() == savingsAccountButtonWithdraw)
                || (e.getSource() == checkingAccountButtonWithdraw) || (e.getSource() == confirmDepositButtonSavings)) {
            actionPerformedHelper(e);
        }  else if (e.getSource() == confirmDepositButtonChecking) {
            confirmDepositButtonCheckingAction();
        } else if (e.getSource() == confirmWithdrawButtonSavings) {
            confirmWithdrawButtonSavingsAction();
        } else if (e.getSource() == confirmWithdrawButtonChecking) {
            confirmWithdrawButtonCheckingAction();
        } else if (e.getSource() == savingsToCheckingAccountButtonTransfer) {
            savingsToCheckingAccountButtonTransferAction();
        } else if (e.getSource() == checkingToSavingsAccountButtonTransfer) {
            checkingToSavingsAccountButtonTransferAction();
        } else if (e.getSource() == confirmSavingsToCheckingAccountButtonTransfer) {
            confirmSavingsToCheckingAccountButtonTransferAction();
        } else if (e.getSource() == confirmCheckingToSavingsAccountButtonTransfer) {
            confirmCheckingToSavingsAccountButtonTransferAction();
        }
    }

    private void loginButtonAction() {
        String userInputText = userInput.getText();
        String passwordInputText = passwordInput.getText();
        System.out.println(userInputText + ", " + passwordInputText);
        if (userInputText.equals(tryUser.getUsername())
                && passwordInputText.equals(Integer.toString(tryUser.getPassword()))) {
            loginPassOrFail.setText("Login successful!");
            Sound.playSound("Music/buttonSound.wav");
            // try to close the login screen once login is successful
            guiAtmModel();
        } else {
            loginPassOrFail.setText("Username/Password combination incorrect.");
        }
    }

    private void confirmCheckingToSavingsAccountButtonTransferAction() {
        if (Double.parseDouble(amountEnteredTransfer.getText()) <= checking.getBalancee()) {
            checking.withdraw(Double.parseDouble(amountEnteredTransfer.getText()));
            saving.deposit(Double.parseDouble(amountEnteredTransfer.getText()));
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\nTransferred %s from "
                            + "Checking to Savings Account",
                    amountEnteredTransfer.getText()));
            Sound.playSound("Music/transferSound.wav");
        } else {
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\n Unable to Transfer, "
                            + "%s is greater than Checking account balance",
                    amountEnteredTransfer.getText()));
        }
    }

    private void confirmSavingsToCheckingAccountButtonTransferAction() {
        if (Double.parseDouble(amountEnteredTransfer.getText()) <= saving.getBalancee()) {
            saving.withdraw(Double.parseDouble(amountEnteredTransfer.getText()));
            checking.deposit(Double.parseDouble(amountEnteredTransfer.getText()));
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\nTransferred %s from "
                            + "Savings to Checking Account",
                    amountEnteredTransfer.getText()));
            Sound.playSound("Music/transferSound.wav");
        } else {
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\n Unable to Transfer, "
                            + "%s is greater than Saving account balance",
                    amountEnteredTransfer.getText()));
        }
    }

    private void checkingToSavingsAccountButtonTransferAction() {
        Sound.playSound("Music/buttonSound.wav");
        checkingToSavingsAccountTransfer();
    }

    private void savingsToCheckingAccountButtonTransferAction() {
        Sound.playSound("Music/buttonSound.wav");
        savingsToCheckingAccountTransfer();
    }

    private void confirmWithdrawButtonCheckingAction() {
        if (Double.parseDouble(amountEnteredWithdraw.getText()) <= checking.getBalancee()) {
            checking.withdraw(Double.parseDouble(amountEnteredWithdraw.getText()));
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\nWithdrew %s from Checking Account",
                    amountEnteredWithdraw.getText()));
            Sound.playSound("Music/trimmedWithdrawSound.wav");
        } else {
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append("\n Unable to Withdraw, "
                            + "amount is greater than account balance");
        }
    }

    private void confirmWithdrawButtonSavingsAction() {
        if (Double.parseDouble(amountEnteredWithdraw.getText()) <= saving.getBalancee()) {
            saving.withdraw(Double.parseDouble(amountEnteredWithdraw.getText()));
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\nWithdrew %s from Savings Account",
                    amountEnteredWithdraw.getText()));
            Sound.playSound("Music/trimmedWithdrawSound.wav");
        } else {
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append("\n Unable to Withdraw, "
                            + "amount is greater than account balance");
        }
    }

    private void confirmDepositButtonCheckingAction() {
        try {
            checking.deposit(Double.parseDouble(amountEnteredDeposit.getText()));
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\nDeposited %s into Checking Account",
                    amountEnteredDeposit.getText()));
            Sound.playSound("Music/chaChing.wav");
        } catch (NumberFormatException z) {
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append("\nPlease enter a valid amount");
        }
    }

    private void confirmDepositButtonSavingsAction() {
        try {
            saving.deposit(Double.parseDouble(amountEnteredDeposit.getText()));
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append(String.format("\nDeposited %s into Savings Account",
                    amountEnteredDeposit.getText()));
            Sound.playSound("Music/chaChing.wav");
        } catch (NumberFormatException z) {
            textAreaBottomHalf.setText("");
            textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                    tryUser.getFirstName(), checking.getBalancee()));
            textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                    tryUser.getFirstName(), saving.getBalancee()));
            textAreaBottomHalf.append("\nPlease enter a valid amount");
        }
    }

    private void checkingAccountButtonWithdrawAction() {
        Sound.playSound("Music/buttonSound.wav");
        checkingAccountWithdraw();
    }

    private void savingsAccountButtonWithdrawAction() {
        Sound.playSound("Music/buttonSound.wav");
        savingsAccountWithdraw();
    }

    private void checkingAccountButtonDepositAction() {
        Sound.playSound("Music/buttonSound.wav");
        checkingAccountDeposit();
    }

    private void savingsAccountButtonDepositAction() {
        Sound.playSound("Music/buttonSound.wav");
        savingsAccountDeposit();
    }

    private void exitButtonAction() {
        Sound.playSound("Music/buttonSound.wav");
        System.exit(0);
    }

    private void saveAccountsButtonAction() {
        Sound.playSound("Music/buttonSound.wav");
        saveAccounts();
    }

    private void transferButtonAction() {
        Sound.playSound("Music/buttonSound.wav");
        textAreaTopHalf.setText("");
        textAreaTopHalf.append("ATM in transfer mode\n"
                + "Please select account to transfer from:");
        transferPanel();
    }

    private void depositButtonAction() {
        Sound.playSound("Music/buttonSound.wav");
        textAreaTopHalf.setText("");
        textAreaTopHalf.append("ATM in deposit mode\n"
                + "Please select an Account to deposit to:");
        depositPanel();
    }

    private void withdrawButtonAction() {
        Sound.playSound("Music/buttonSound.wav");
        textAreaTopHalf.setText("");
        textAreaTopHalf.append("ATM in withdraw mode\n"
                + "Please select Account to withdraw from:");
        withdrawPanel();
    }

    // EFFECTS: saves state of chequing and savings accounts to ACCOUNTS_FILE
    public static void saveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(checking);
            writer.write(saving);
            writer.close();
            textAreaTopHalf.setText("Accounts saved to file ./data/accounts.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    public static void guiAtmModel() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(600, 650));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(null); // CHANGE THIS TO GRID???
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel.setBackground(LIGHT_BLUE);
        guiAtmModelHelper(panel);
        textAreaTopHalf = new JTextArea();
        textAreaTopHalf.setEditable(false);
        textAreaTopHalf.setBounds(20, 285, 550, 150);
        panel.add(textAreaTopHalf);
        textAreaBottomHalf = new JTextArea();
        textAreaBottomHalf.setEditable(false);
        textAreaBottomHalf.setBounds(20, 435, 550, 150);
        panel.add(textAreaBottomHalf);
        loadAccounts();
        guiAtmModelHelper1();


    }

    private static void guiAtmModelHelper1() {
        textAreaBottomHalf.append(String.format("Balance on %s's CHECKING Account: %s\n",
                tryUser.getFirstName(), checking.getBalancee()));
        textAreaBottomHalf.append(String.format("Balance on %s's SAVING Account: %s",
                tryUser.getFirstName(), saving.getBalancee()));
    }

    private static void guiAtmModelHelper(JPanel panel) {
        guiAtmModelHelpersHelper(panel);
        withdrawButton = new JButton("Withdraw : ");
        withdrawButton.setBounds(20, 105, 250, 25);
        withdrawButton.addActionListener(new GUI());
        panel.add(withdrawButton);
        // unmodifiable "Deposit" button
        depositButton = new JButton("Deposit : ");
        depositButton.setBounds(20, 135, 250, 25);
        panel.add(depositButton);
        depositButton.addActionListener(new GUI());
        // unmodifiable "Transfer" button
        transferButton = new JButton("Transfer Between Accounts :");
        transferButton.setBounds(20, 165, 250, 25);
        panel.add(transferButton);
        transferButton.addActionListener(new GUI());
        // unmodifiable "Save Accounts" button
        saveAccountsButton = new JButton("Save Accounts :");
        saveAccountsButton.setBounds(20, 195, 250, 25);
        panel.add(saveAccountsButton);
        saveAccountsButton.addActionListener(new GUI());
        // unmodifiable "Exit" button
        exitButton = new JButton("Exit :");
        exitButton.setBounds(20, 225, 250, 25);
        panel.add(exitButton);
        exitButton.addActionListener(new GUI());
    }

    private static void guiAtmModelHelpersHelper(JPanel panel) {
        // unmodifiable "Welcome" text
        welcomeLabel = new JLabel("Hello Jagmeet !  How are you today ?");
        welcomeLabel.setBounds(200, 0, 250, 100);
        panel.add(welcomeLabel);
        // unmodifiable "Password" text
        selectOptionsLabel = new JLabel("Please select from the following options:");
        selectOptionsLabel.setBounds(10, 75, 250, 25);
        panel.add(selectOptionsLabel);
        // unmodifiable "Withdraw" button
    }

    public static void withdrawPanel() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);

        selectAccountLabelWithdraw = new JLabel("Please Select An Account To Withdraw From");
        selectAccountLabelWithdraw.setBounds(20, 20, 200, 40);
        panel.add(selectAccountLabelWithdraw);
        savingsAccountButtonWithdraw = new JButton("Savings");
        savingsAccountButtonWithdraw.setBounds(315, 105, 100, 25);
        panel.add(savingsAccountButtonWithdraw);
        savingsAccountButtonWithdraw.addActionListener(new GUI());
        checkingAccountButtonWithdraw = new JButton("Checking");
        checkingAccountButtonWithdraw.setBounds(425, 105, 100, 25);
        panel.add(checkingAccountButtonWithdraw);
        checkingAccountButtonWithdraw.addActionListener(new GUI());
        amountEnteredWithdraw = new JTextField(10);
        amountEnteredWithdraw.setBounds(100, 100, 100, 100);
    }

    public static void savingsAccountWithdraw() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);
        panel.add(enterAmountLabel);
        panel.add(amountEnteredWithdraw);
        savingAccountExplanationLabel = new JLabel("This will be withdrawn from your savings account");
        panel.add(savingAccountExplanationLabel);
        confirmWithdrawButtonSavings = new JButton("Make Withdrawal");
        panel.add(confirmWithdrawButtonSavings);
        confirmWithdrawButtonSavings.addActionListener(new GUI());
    }

    public static void checkingAccountWithdraw() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);
        panel.add(enterAmountLabel);
        panel.add(amountEnteredWithdraw);
        checkingAccountExplanationLabel = new JLabel("This will be withdrawn from your checking account");
        panel.add(checkingAccountExplanationLabel);
        confirmWithdrawButtonChecking = new JButton("Make Withdrawal");
        panel.add(confirmWithdrawButtonChecking);
        confirmWithdrawButtonChecking.addActionListener(new GUI());

    }

    public static void depositPanel() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);

        selectAccountLabelDeposit = new JLabel("Please Select An Account To Deposit Too");
        selectAccountLabelDeposit.setBounds(20, 20, 200, 40);
        panel.add(selectAccountLabelDeposit);

        savingsAccountButtonDeposit = new JButton("Savings");
        savingsAccountButtonDeposit.setBounds(315, 105, 100, 25);
        panel.add(savingsAccountButtonDeposit);
        savingsAccountButtonDeposit.addActionListener(new GUI());
        checkingAccountButtonDeposit = new JButton("Checking");
        checkingAccountButtonDeposit.setBounds(425, 105, 100, 25);
        panel.add(checkingAccountButtonDeposit);
        checkingAccountButtonDeposit.addActionListener(new GUI());
        amountEnteredDeposit = new JTextField(10);
        amountEnteredDeposit.setBounds(100, 100, 100, 100);
        //panel.add(amountEnteredDeposit);

    }

    public static void savingsAccountDeposit() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);
        panel.add(enterAmountLabel);
        panel.add(amountEnteredDeposit);
        savingAccountExplanationLabel = new JLabel("This will be deposited in the checking account");
        panel.add(savingAccountExplanationLabel);
        confirmDepositButtonSavings = new JButton("Make Deposit");
        panel.add(confirmDepositButtonSavings);
        confirmDepositButtonSavings.addActionListener(new GUI());

    }

    public static void checkingAccountDeposit() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);
        panel.add(enterAmountLabel);
        panel.add(amountEnteredDeposit);
        checkingAccountExplanationLabel = new JLabel("This will be deposited in the checking account");
        panel.add(checkingAccountExplanationLabel);
        confirmDepositButtonChecking = new JButton("Make Deposit");
        panel.add(confirmDepositButtonChecking);
        confirmDepositButtonChecking.addActionListener(new GUI());


    }

    public static void transferPanel() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);

        selectAccountLabelTransfer = new JLabel("Please Select A Transfer Method");
        selectAccountLabelTransfer.setBounds(20, 20, 200, 40);
        panel.add(selectAccountLabelTransfer);

        savingsToCheckingAccountButtonTransfer = new JButton("Savings to Checking");
        savingsToCheckingAccountButtonTransfer.setBounds(315, 105, 100, 25);
        panel.add(savingsToCheckingAccountButtonTransfer);
        savingsToCheckingAccountButtonTransfer.addActionListener(new GUI());
        checkingToSavingsAccountButtonTransfer = new JButton("Checking to Savings");
        checkingToSavingsAccountButtonTransfer.setBounds(425, 105, 100, 25);
        panel.add(checkingToSavingsAccountButtonTransfer);
        checkingToSavingsAccountButtonTransfer.addActionListener(new GUI());
        amountEnteredTransfer = new JTextField(10);
        amountEnteredTransfer.setBounds(100, 100, 100, 100);
    }

    public static void savingsToCheckingAccountTransfer() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);
        panel.add(enterAmountLabel);
        panel.add(amountEnteredTransfer);
        transferAccountExplanationLabel = new JLabel("Transfer money from savings"
                + " to checking");
        panel.add(transferAccountExplanationLabel);
        confirmSavingsToCheckingAccountButtonTransfer = new JButton("Make transfer");
        panel.add(confirmSavingsToCheckingAccountButtonTransfer);
        confirmSavingsToCheckingAccountButtonTransfer.addActionListener(new GUI());
    }

    public static void checkingToSavingsAccountTransfer() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(280, 150));
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setResizable(false);
        frame.setLocation(940, 323);
        panel.setBackground(LIGHT_BLUE);
        panel.add(enterAmountLabel);
        panel.add(amountEnteredTransfer);
        transferAccountExplanationLabel = new JLabel("Transfer money from checking"
                + " to saving");
        panel.add(transferAccountExplanationLabel);
        confirmCheckingToSavingsAccountButtonTransfer = new JButton("Make transfer");
        panel.add(confirmCheckingToSavingsAccountButtonTransfer);
        confirmCheckingToSavingsAccountButtonTransfer.addActionListener(new GUI());
    }
}
