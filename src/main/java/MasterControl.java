import java.util.List;

public class MasterControl {
    Bank bank;
    CreateValidator createValidator;
    DepositValidator depositValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;

    public MasterControl(Bank bank, CreateValidator createValidator, DepositValidator depositValidator, CommandProcessor commandProcessor, CommandStorage commandStorage) {
        this.bank = bank;
        this.createValidator = createValidator;
        this.depositValidator = depositValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
    }

    public List<String> start(List<String> input) {
        commandStorage.addInvalidCommand(input.get(0));
        return commandStorage.getInvalidCommands();
    }
}
