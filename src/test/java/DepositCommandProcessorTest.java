import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositCommandProcessorTest {
    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    public void deposit_into_account_that_was_just_created() {
        commandProcessor.processCommand("create checking 23457643 0.03");
        commandProcessor.processCommand("deposit 23457643 100");
        assertEquals(100, bank.getAccounts().get(23457643).getBalance());
    }

    @Test
    public void deposit_into_account_that_already_has_balance() {
        commandProcessor.processCommand("create checking 23457643 0.03");
        commandProcessor.processCommand("deposit 23457643 100");
        commandProcessor.processCommand("deposit 23457643 600");
        assertEquals(700, bank.getAccounts().get(23457643).getBalance());
    }

    @Test
    public void deposit_into_cd_account() {
        commandProcessor.processCommand("create cd 23457643 0.03 300");
        commandProcessor.processCommand("deposit 23457643 100");
        assertEquals(400, bank.getAccounts().get(23457643).getBalance());
    }

    @Test
    public void deposit_into_same_account_twice() {
        commandProcessor.processCommand("create savings 23457643 0.03");
        commandProcessor.processCommand("deposit 23457643 400");
        commandProcessor.processCommand("deposit 23457643 600");
        assertEquals(1000, bank.getAccounts().get(23457643).getBalance());
    }

    @Test
    public void deposit_into_two_different_accounts() {
        commandProcessor.processCommand("create checking 23457643 0.03");
        commandProcessor.processCommand("create savings 23890768 0.03");
        commandProcessor.processCommand("deposit 23457643 100");
        commandProcessor.processCommand("deposit 23890768 600");
        assertEquals(100, bank.getAccounts().get(23457643).getBalance());
        assertEquals(600, bank.getAccounts().get(23890768).getBalance());
    }

}
