package atmTest;

import atm.Account;
import atm.Bank;
import atm.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {


    @Test
    void getAmountTest() {

        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        Account acct = new Account("Savings", 0, bankName, userInfo, "TD");
        Account account = new Account("Savings",0, bankName, userInfo, "TD");
        assertEquals(0,userInfo.getAcctBalance((int) acct.getBalance()));

    }
}
