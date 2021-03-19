package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCommandProcessorTest {
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
    public void transfer_between_checking_to_savings_account() {
        commandProcessor.processCommand("deposit 13748594 600");
        commandProcessor.processCommand("deposit 23746537 500");
        commandProcessor.processCommand("transfer 13748594 23746537 100");
        assertEquals(600, bank.getAccounts().get(23746537).getBalance());
        assertEquals(500, bank.getAccounts().get(13748594).getBalance());
    }

    @Test
    public void transfer_between_two_checking_accounts() {
        bank.addCheckingAccount("Checking", 12786547, 0.02);
        commandProcessor.processCommand("deposit 13748594 600");
        commandProcessor.processCommand("transfer 13748594 12786547 200");
        assertEquals(200, bank.getAccounts().get(12786547).getBalance());
        assertEquals(400, bank.getAccounts().get(13748594).getBalance());
    }

    @Test
    public void transfer_between_two_savings_accounts() {
        bank.addSavingsAccount("Savings", 23784983, 0.02);
        commandProcessor.processCommand("deposit 23784983 800");
        commandProcessor.processCommand("transfer 23784983 23746537 800");
        assertEquals(0, bank.getAccounts().get(23784983).getBalance());
        assertEquals(800, bank.getAccounts().get(23746537).getBalance());
    }

    @Test
    public void transfer_twice_from_same_account() {
        commandProcessor.processCommand("deposit 13748594 800");
        commandProcessor.processCommand("transfer 13748594 23746537 200");
        commandProcessor.processCommand("transfer 13748594 23746537 100");
        assertEquals(500, bank.getAccounts().get(13748594).getBalance());
        assertEquals(300, bank.getAccounts().get(23746537).getBalance());
    }

    @Test
    public void transfer_twice_between_two_accounts() {
        bank.addCheckingAccount("Checking", 12784567, 0.02);
        commandProcessor.processCommand("deposit 13748594 800");
        commandProcessor.processCommand("transfer 13748594 12784567 500");
        commandProcessor.processCommand("transfer 12784567 13748594 200");
        assertEquals(500, bank.getAccounts().get(13748594).getBalance());
        assertEquals(300, bank.getAccounts().get(12784567).getBalance());
    }

}
