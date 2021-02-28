import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {
    CommandStorage commandStorage;

    @BeforeEach
    public void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    public void add_invalid_command() {
        commandStorage.addInvalidCommand("invalid command");
        assertTrue(commandStorage.getInvalidCommands().get(0).equals("invalid command"));
    }

}
