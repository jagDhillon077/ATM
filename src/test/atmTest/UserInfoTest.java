package atmTest;
import atm.Transaction;
import atm.UserInfo;
import atm.Bank;
import atm.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.AtmMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInfoTest {
    private AtmMain testAtmMain;

    @BeforeEach
    void runBefore() {
        testAtmMain = new AtmMain();
    }

    @Test
    void userInfoTest() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        assertEquals("jagmeet",userInfo.getFirstName());
        assertEquals("dhillon",userInfo.getLastName());
        assertEquals("jagmdhi",userInfo.getUsername());
        assertEquals(111111,userInfo.getPassword());
    }



/*
    @Test
    void getAcctBalanceTest() {
        int acct;
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        assertEquals(0,userInfo.getAcctBalance());
    }

*/
}
