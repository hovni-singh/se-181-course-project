package banking;

import java.util.List;

public class MasterControl {
    Bank bank;
    CreateValidator createValidator;
    DepositValidator depositValidator;
    WithdrawValidator withdrawValidator;
    TransferValidator transferValidator;
    PassTimeValidator passTimeValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;

    public MasterControl(Bank bank, CreateValidator createValidator, DepositValidator depositValidator, WithdrawValidator withdrawValidator, TransferValidator transferValidator, PassTimeValidator passTimeValidator, CommandProcessor commandProcessor, CommandStorage commandStorage) {
        this.bank = bank;
        this.createValidator = createValidator;
        this.depositValidator = depositValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
        this.withdrawValidator = withdrawValidator;
        this.transferValidator = transferValidator;
        this.passTimeValidator = passTimeValidator;
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
