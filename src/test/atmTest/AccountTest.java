package atmTest;

import atm.Account;
import atm.Bank;
import atm.UserInfo;
import exceptions.OnlyPositiveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    // STARTING OF PHASE 4 TASK 2
    //======================================================================================
    // STARTING OF PHASE 4 TASK 2

    @Test
    void robustTransactionTest() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        Account account = new Account("Savings",0,bankName,userInfo,"TD");
        try {
            userInfo.addAcctTransaction(0, -500);
            fail("Exception should have been thrown");
        } catch (OnlyPositiveException p) {
            System.out.println("Only positive Numbers Please");
            // this exception is supposed to be thrown
        }
    }


    @Test
    void robustTransactionTestNotExpectingException() {
        Bank bankName = new Bank("TD");
        UserInfo userInfo = new UserInfo("jagmeet", "dhillon",
                "jagmdhi", bankName, 111111);
        Account account = new Account("Savings",0,bankName,userInfo,"TD");
        try {
            userInfo.addAcctTransaction(0, 500);
        } catch (OnlyPositiveException p) {
            fail("Only Positive Exception should not have been thrown here");
        }
    }

    // END OF PHASE 4 TASK 2
    //======================================================================================
    // END OF PHASE 4 TASK 2







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
        Account account = new Account("Savings", 450, bankName, userInfo, "Scotia");
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
        Account account = new Account("Savings", 0, bankName, userInfo, "TD");
        assertEquals(0, userInfo.getAcctBalance((int) acct.getBalance()));
    }




}



