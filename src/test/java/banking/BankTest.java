package banking;

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
        bank.addSavingsAccount("banking.Checking", ID, APR);
        assertTrue(bank.accountExists(ID));
    }

    @Test
    public void add_cd_account_to_bank() {
        bank.addCDAccount("banking.CD", ID, APR, 20);
        assertTrue(bank.accountExists(ID));
        assertFalse(bank.accountExists(90234785));
    }

    @Test
    public void add_checking_account_to_bank() {
        bank.addCheckingAccount("banking.Checking", ID, APR);
        assertTrue(bank.accountExists(ID));
        assertTrue(bank.getAccounts().get(ID).getType() == "banking.Checking");
    }

    @Test
    public void add_two_checking_accounts() {
        bank.addCheckingAccount("banking.Checking", ID, APR);
        bank.addCheckingAccount("banking.Checking", 17829345, APR);
        assertTrue(bank.accountExists(ID) && bank.accountExists(17829345));
    }

    @Test
    public void add_two_different_accounts() {
        bank.addCheckingAccount("banking.Checking", ID, APR);
        bank.addSavingsAccount("banking.Savings", 39473234, APR);
        assertTrue((bank.getAccounts().get(ID).getType() == "banking.Checking") && (bank.getAccounts().get(39473234).getType() == "banking.Savings"));
        assertTrue(bank.accountExists(ID) && bank.accountExists(39473234));
    }


    @Test
    public void deposit_into_account() {
        bank.addSavingsAccount("banking.Savings", ID, APR);
        bank.getAccounts().get(ID).deposit(50);
        assertEquals(50, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void deposit_two_times_into_account() {
        bank.addCheckingAccount("banking.Checking", ID, APR);
        bank.getAccounts().get(ID).deposit(100);
        bank.getAccounts().get(ID).deposit(300);
        assertEquals(400, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_from_account() {
        bank.addSavingsAccount("banking.Savings", ID, APR);
        bank.getAccounts().get(ID).deposit(70);
        bank.getAccounts().get(ID).withdraw(30);
        assertEquals(40, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_more_than_account_balance() {
        bank.addCheckingAccount("banking.Checking", ID, APR);
        bank.getAccounts().get(ID).deposit(200);
        bank.getAccounts().get(ID).withdraw(500);
        assertEquals(0, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    public void withdraw_two_times_from_account() {
        bank.addCheckingAccount("banking.Checking", ID, APR);
        bank.getAccounts().get(ID).deposit(2000);
        bank.getAccounts().get(ID).withdraw(100);
        bank.getAccounts().get(ID).withdraw(500);
        assertEquals(1400, bank.getAccounts().get(ID).getBalance());
    }


    @Test
    public void deposit_and_withdraw_from_account() {
        bank.addSavingsAccount("banking.Savings", ID, APR);
        bank.getAccounts().get(ID).deposit(400);
        bank.getAccounts().get(ID).withdraw(200);
        assertEquals(200, bank.getAccounts().get(ID).getBalance());
    }

}
