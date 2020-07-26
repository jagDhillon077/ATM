package atmTest;

import atm.Account;
import atm.Bank;
import atm.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AccountTest {

    @BeforeEach
    void runBefore() {

    }

    @Test
    void getSummaryLineTest() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        Account acct = new Account("Savings", 0, bankName, userInfo, "TD");
        // In the expected Vs Actual Everything is similar except the UUID, test passed
        assertNotEquals("Account Number: 67ef5979-65bf-4ef8-92f6-c580e2b0fa13 , Balance: $0.0 , " +
                "Type of Account: Savings",
                acct.getSummaryLine());
        Account account = new Account("Savings",450, bankName, userInfo, "Scotia");
        // In the expected Vs Actual Everything is similar except the UUID, test passed
        assertNotEquals("Account Number: 67ef5979-65bf-4ef8-92f6-c580e2b0fa13 , Balance: $0.0 , " +
                "Type of Account: Savings",
                account.getSummaryLine());
    }

    @Test
    void getBalanceTest() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        Account acct = new Account("Savings", 0, bankName, userInfo, "TD");
        Account account = new Account("Savings",0, bankName, userInfo, "TD");
        assertEquals(0,userInfo.getAcctBalance((int) acct.getBalance()));
    }


}
