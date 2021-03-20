package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {
    CommandStorage commandStorage;

    @BeforeEach
    public void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    public void initially_empty_test() {
        assertEquals(0, commandStorage.getInvalidCommands().size());
    }

    @Test
    public void add_invalid_command() {
        commandStorage.addInvalidCommand("invalid command");
        assertTrue(commandStorage.getInvalidCommands().get(0).equals("invalid command"));
    }

    @Test
    public void get_all_invalid_command_strings() {
        commandStorage.addInvalidCommand("invalid command 1");
        commandStorage.addInvalidCommand("invalid command 2");
        commandStorage.addInvalidCommand("invalid command 3");
        assertEquals(3, commandStorage.getInvalidCommands().size());
    }


}
