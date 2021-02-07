import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountTest {
    private static final int ID = 98672384;
    private static final double APR = 0.03;
    Account checkingAccount;
    Account savingsAccount;
    Account cdAccount;

    @BeforeEach
    public void setUp() {
        checkingAccount = new Checking("checking", ID, APR);
        savingsAccount = new Savings("savings", ID, APR);
        cdAccount = new CD("cd", ID, APR, 0);
    }

    @Test
    public void balance_initially_zero() {
        assertEquals(0, checkingAccount.getBalance());
    }

    @Test
    public void account_has_type() {
        assertNotNull(savingsAccount.getType());
    }

    @Test
    public void account_has_id() {
        assertNotNull(cdAccount.getId());
    }

    @Test
    public void account_has_apr() {
        assertNotNull(checkingAccount.getAPR());
    }

    @Test
    public void cd_account_has_amount() {
        assertNotNull(cdAccount.getBalance());
    }

}