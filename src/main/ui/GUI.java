package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import ui.ATM;

public class GUI extends JFrame implements ActionListener {
    private static JLabel usernameLabel;
    private static JTextField userInput;
    private static JLabel passwordLabel;
    private static JPasswordField passwordInput;
    private static JButton button;
    private static JLabel loginPassOrFail;


    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(null); // CHANGE THIS TO GRID???
        frame.setVisible(true);
        frame.add(panel);
        // unmodifiable "Username" text
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 20, 80, 25);
        panel.add(usernameLabel);
        // unmodifiable "Password" text
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        // username input field
        userInput = new JTextField(20);
        userInput.setBounds(100, 20, 165, 25);
        panel.add(userInput);
        // password input field
        passwordInput = new JPasswordField(20);
        passwordInput.setBounds(100, 50, 165, 25);
        panel.add(passwordInput);
        // SPECIFIC PASSWORD FIELD FOR HIDDEN PASSWORDS
        // guiLoginScreenHelper prevents method from being 25+ lines
        guiLoginScreenHelper(panel);

        frame.setResizable(false);
    }

    public static void guiLoginScreenHelper(JPanel panel) {
        // login attempt button
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);
        // login pass or fail notifier
        loginPassOrFail = new JLabel("");
        loginPassOrFail.setBounds(10, 110, 300, 25);
        panel.add(loginPassOrFail);
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    public void actionPerformed(ActionEvent e) {
        String user = userInput.getText();
        String password = passwordInput.getText();
        System.out.println(user + ", " + password);
        if (user.equals("1") && password.equals("1")) {
            loginPassOrFail.setText("Login successful!");
            // try to close the login screen once login is successful
            guiAtmModel();
        } else {
            loginPassOrFail.setText("Username/Password combination incorrect.");
        }
    }

    public static void guiAtmModel() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(null); // CHANGE THIS TO GRID???
        frame.setVisible(true);
        frame.add(panel);
        // unmodifiable "Welcome" text
        usernameLabel = new JLabel("Welcome to TD Bank");
        usernameLabel.setBounds(25, 20, 80, 25);
        panel.add(usernameLabel);
        // unmodifiable "Password" text
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        // username input field
        userInput = new JTextField(20);
        userInput.setBounds(100, 20, 165, 25);
        panel.add(userInput);
        // password input field
        passwordInput = new JPasswordField(20);
        passwordInput.setBounds(100, 50, 165, 25);
        panel.add(passwordInput);
    }
}
