package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandProcessorTest {
    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    public void bank_has_checking_account_with_correct_id() {
        commandProcessor.processCommand("create checking 12378645 0.03");
        assertEquals(12378645, bank.getAccounts().get(12378645).getId());
        assertEquals("checking", bank.getAccounts().get(12378645).getType());
    }

    @Test
    public void bank_has_checking_account_with_correct_apr() {
        commandProcessor.processCommand("create checking 12378646 0.03");
        assertEquals(0.03, bank.getAccounts().get(12378646).getAPR());
        assertEquals("checking", bank.getAccounts().get(12378646).getType());
    }

    @Test
    public void bank_has_savings_account_with_correct_apr() {
        commandProcessor.processCommand("create savings 18763457 0.05");
        assertEquals(18763457, bank.getAccounts().get(18763457).getId());
        assertEquals("savings", bank.getAccounts().get(18763457).getType());
    }

    @Test
    public void bank_has_savings_account_with_correct_id() {
        commandProcessor.processCommand("create savings 18763456 0.05");
        assertEquals(0.05, bank.getAccounts().get(18763456).getAPR());
        assertEquals("savings", bank.getAccounts().get(18763456).getType());
    }

    @Test
    public void bank_has_cd_account_with_correct_id() {
        commandProcessor.processCommand("create cd 23765456 0.04 200");
        assertEquals("cd", bank.getAccounts().get(23765456).getType());
        assertEquals(23765456, bank.getAccounts().get(23765456).getId());
    }

    @Test
    public void bank_has_cd_account_with_correct_apr() {
        commandProcessor.processCommand("create cd 23765456 0.04 200");
        assertEquals("cd", bank.getAccounts().get(23765456).getType());
        assertEquals(0.04, bank.getAccounts().get(23765456).getAPR());
    }

    @Test
    public void bank_has_cd_account_with_correct_initial_balance() {
        commandProcessor.processCommand("create cd 23765456 0.04 200");
        assertEquals("cd", bank.getAccounts().get(23765456).getType());
        assertEquals(200, bank.getAccounts().get(23765456).getBalance());
    }


    @Test
    public void bank_has_two_checking_accounts() {
        commandProcessor.processCommand("create checking 23765456 0.04");
        commandProcessor.processCommand("create checking 12387609 0.03");
        boolean actual = bank.getAccounts().get(23765456).getType().equals("checking");
        boolean actual1 = bank.getAccounts().get(12387609).getType().equals("checking");
        assertTrue(actual & actual1);
    }

    @Test
    public void bank_has_two_savings_accounts() {
        commandProcessor.processCommand("create savings 23765456 0.04");
        commandProcessor.processCommand("create savings 12387609 0.03");
        boolean actual = bank.getAccounts().get(23765456).getType().equals("savings");
        boolean actual1 = bank.getAccounts().get(12387609).getType().equals("savings");
        assertTrue(actual & actual1);
    }

    @Test
    public void bank_has_two_cd_accounts() {
        commandProcessor.processCommand("create cd 23765456 0.04 300");
        commandProcessor.processCommand("create cd 12387609 0.03 300");
        boolean actual = bank.getAccounts().get(23765456).getType().equals("cd");
        boolean actual1 = bank.getAccounts().get(12387609).getType().equals("cd");
        assertTrue(actual & actual1);
    }

    @Test
    public void bank_has_two_cd_accounts_with_correct_initial_balances() {
        commandProcessor.processCommand("create cd 23765456 0.04 300");
        commandProcessor.processCommand("create cd 12387609 0.03 400");
        boolean actual = bank.getAccounts().get(23765456).getBalance() == 300;
        boolean actual1 = bank.getAccounts().get(12387609).getBalance() == 400;
        assertTrue(actual & actual1);
    }

    @Test
    public void bank_has_two_different_accounts() {
        commandProcessor.processCommand("create checking 23765456 0.04");
        commandProcessor.processCommand("create savings 12387609 0.03");
        boolean actual = bank.getAccounts().get(23765456).getType().equals("checking");
        boolean actual1 = bank.getAccounts().get(12387609).getType().equals("savings");
        assertTrue(actual & actual1);
    }


}
