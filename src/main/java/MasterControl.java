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
        for (String command : input) {
            if (createValidator.createValidate(command) || depositValidator.depositValidate(command)) {
                commandProcessor.processCommand(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        return commandStorage.getInvalidCommands();
    }

}
