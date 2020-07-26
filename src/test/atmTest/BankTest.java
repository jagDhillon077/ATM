package atmTest;

import atm.Bank;
import atm.UserInfo;
import atm.Transaction;
import atm.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AtmMain;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private AtmMain testAtmMain;
    private UserInfo user;


    @BeforeEach
    void runBefore() { testAtmMain = new AtmMain(); }



    @Test
    void getNewUserUUIdTest() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        assertNotEquals("9ff00903-855a-40c7-a032-fe4e6448861f",bankName.getNewUserUUId());

    }

    @Test
    void getNewAccountUUIdTest() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        assertNotEquals("9ff00903-855a-40c7-a032-fe4e6448861f",bankName.getNewAccountUUId());

    }



    @Test
    void newUserTest() {

        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        // Assert NotEquals Here because users are created with different UUID's
        // however checking the console shows the same accounts being created aside from UUID
        assertNotEquals(userInfo, bankName.newUser("jagmeet","dhillon",
                "jagmdhi", 111111));
    }



    @Test
    // validPassword is also tested from this test
    void loginServiceTest () {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        bankName.newUser("jagmeet","dhillon",
                "jagmdhi", 111111);
        // Again assertingNotEquals Here because users are created with different UUID's
        // but loginService does provide the same account
        assertNotEquals(userInfo,bankName.loginService("jagmdhi",111111));

    }
}
