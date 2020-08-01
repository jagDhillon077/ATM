package persistenceTest;



import atm.Account;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    private Object IndexOutOfBoundsException;
    // tests from TellerApp ass the tests perfectly fit the needs of the program
    @Test
    void testParseAccountsAccounts() {
        try {
            List<Account> accounts = Reader.readAccounts(new File("./data/testAccounts.txt"));
            Account chequing = accounts.get(0);
            assertEquals(123.56, chequing.getBalancee());

            Account savings = accounts.get(1);
            assertEquals(435.23, savings.getBalancee());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsAccounts2() {
        try {
            List<Account> accounts = Reader.readAccounts(new File("./data/testAccounts2.txt"));
            Account chequing = accounts.get(0);
            assertEquals(10000.0, chequing.getBalancee());

            Account savings = accounts.get(1);
            assertEquals(9000.0, savings.getBalancee());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIndexOutOfBoundsException() {

        try {
            List<Account> accounts = Reader.readAccounts(new File("./data/testAccounts2.txt"));
            try {
                Account savings = accounts.get(3);
                assertEquals(0, savings.getBalancee());
            } catch (IndexOutOfBoundsException I) { //expected }
            }
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readAccounts(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}