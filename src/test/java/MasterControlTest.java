import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {
    List<String> input;
    MasterControl masterControl;
    Bank bank;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>();
        bank = new Bank();
        masterControl = new MasterControl(bank, new CreateValidator(bank), new DepositValidator(bank), new CommandProcessor(bank), new CommandStorage());
    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("depositt 12345678 100", actual);
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 0.05");
        input.add("depositt 12345678 100");
        List<String> actual = masterControl.start(input);
        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 0.05", actual.get(0));
        assertEquals("depositt 12345678 100", actual.get(1));
    }

    @Test
    void invalid_to_create_accounts_with_same_ID() {
        input.add("create checking 12345678 0.05");
        input.add("create checking 12345678 0.05");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("create checking 12345678 0.05", actual);
    }

    @Test
    void invalid_to_create_cd_without_balance() {
        input.add("create cd 12345678 0.05");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("create cd 12345678 0.05", actual);
    }

    @Test
    void invalid_to_create_accounts_with_character_in_id() {
        input.add("create checking 1234a678 0.05");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("create checking 1234a678 0.05", actual);
    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }
}
