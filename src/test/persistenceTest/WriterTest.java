package persistenceTest;

import atm.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Writer;
import persistence.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {

    // Tests from TellerApp (perfectly fit the needs of my program)
    private static final String TEST_FILE = "./data/testAccounts.txt";
    private Writer testWriter;
    private Account checking;
    private Account savings;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        checking = new Account("Mae", 123.56);
        savings = new Account("Jo", 435.23);
    }

    @Test
    void testWriteAccounts() {
        // save chequing and savings accounts to file
        testWriter.write(checking);
        testWriter.write(savings);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<Account> accounts = Reader.readAccounts(new File(TEST_FILE));
            Account chequing = accounts.get(0);
            assertEquals(123.56, chequing.getSavedBalance());

            Account savings = accounts.get(1);
            assertEquals(435.23, savings.getSavedBalance());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
