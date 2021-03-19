package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawCommandProcessorTest {
    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        bank.addCDAccount("CD", 27382367, 0.04, 300);
    }


    @Test
    public void withdraw_from_checking_account() {
        commandProcessor.processCommand("deposit 13748594 500");
        commandProcessor.processCommand("withdraw 13748594 300");
        assertEquals(200, bank.getAccounts().get(13748594).getBalance());
    }

    @Test
    public void withdraw_from_savings_account() {
        commandProcessor.processCommand("deposit 23746537 600");
        commandProcessor.processCommand("withdraw 23746537 300");
        assertEquals(300, bank.getAccounts().get(23746537).getBalance());
    }

    @Test
    public void withdraw_from_balance_of_zero() {
        commandProcessor.processCommand("withdraw 23746537 300");
        assertEquals(0, bank.getAccounts().get(23746537).getBalance());
    }

    @Test
    public void withdraw_from_account_just_created() {
        bank.addCheckingAccount("Checking", 12674536, 0.02);
        commandProcessor.processCommand("withdraw 12674536 300");
        assertEquals(0, bank.getAccounts().get(12674536).getBalance());
    }

    @Test
    public void withdraw_twice_from_checking_account() {
        commandProcessor.processCommand("deposit 13748594 600");
        commandProcessor.processCommand("withdraw 13748594 300");
        commandProcessor.processCommand("withdraw 13748594 100");
        assertEquals(200, bank.getAccounts().get(13748594).getBalance());
    }

    @Test
    public void withdraw_twice_from_savings_account() {
        commandProcessor.processCommand("deposit 23746537 800");
        commandProcessor.processCommand("withdraw 23746537 300");
        commandProcessor.processCommand("withdraw 23746537 300");
        assertEquals(200, bank.getAccounts().get(23746537).getBalance());
    }


    @Test
    public void withdraw_from_two_different_account_types() {
        commandProcessor.processCommand("deposit 13748594 600");
        commandProcessor.processCommand("deposit 23746537 800");
        commandProcessor.processCommand("withdraw 23746537 300");
        commandProcessor.processCommand("withdraw 13748594 300");
        assertEquals(500, bank.getAccounts().get(23746537).getBalance());
        assertEquals(300, bank.getAccounts().get(13748594).getBalance());

    }

}
