package atm;

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

    // List of Accounts that are owned by the user
    private ArrayList<Account> accounts;

    public UserInfo(String firstName, String lastName, String username, int password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

    }



}
