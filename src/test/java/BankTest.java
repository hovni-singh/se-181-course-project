import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private static final int ID = 98672384;
    private static final double APR = 0.03;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void initially_zero_accounts_in_bank() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    public void add_savings_account_to_bank() {
        bank.addSavingsAccount("Checking", ID, APR);
        assertTrue(bank.accountExists(ID));
    }

    @Test
    public void add_cd_account_to_bank() {
        bank.addCDAccount("CD", ID, APR, 20);
        assertTrue(bank.accountExists(ID));
        assertFalse(bank.accountExists(90234785));
    }

    @Test
    public void add_checking_account_to_bank() {
        bank.addCheckingAccount("Checking", ID, APR);
        assertTrue(bank.accountExists(ID));
        assertTrue(bank.getAccounts().get(ID).getType() == "Checking");
    }


    @Test
    public void deposit_into_account() {
        bank.addSavingsAccount("Savings", ID, APR);
        bank.getAccounts().get(ID).deposit(50);
        assertEquals(50, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void deposit_two_times_into_account() {
        bank.addCheckingAccount("Checking", ID, APR);
        bank.getAccounts().get(ID).deposit(100);
        bank.getAccounts().get(ID).deposit(300);
        assertEquals(400, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_from_account() {
        bank.addSavingsAccount("Savings", ID, APR);
        bank.getAccounts().get(ID).deposit(70);
        bank.getAccounts().get(ID).withdraw(30);
        assertEquals(40, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_more_than_account_balance() {
        bank.addCheckingAccount("Checking", ID, APR);
        bank.getAccounts().get(ID).deposit(200);
        bank.getAccounts().get(ID).withdraw(500);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_two_times_from_account() {
        bank.addCheckingAccount("Checking", ID, APR);
        bank.getAccounts().get(ID).deposit(2000);
        bank.getAccounts().get(ID).withdraw(100);
        bank.getAccounts().get(ID).withdraw(500);
        assertEquals(1400, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_from_cd_account() {
        bank.addCDAccount("CD", ID, APR, 700);
        bank.getAccounts().get(ID).withdraw(500);
        assertEquals(200, bank.getAccounts().get(ID).getBalance());
    }

}
