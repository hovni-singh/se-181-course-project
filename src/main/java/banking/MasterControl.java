package banking;

import java.util.List;

public class MasterControl {
    Bank bank;
    CommandValidator commandValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;

    public MasterControl(Bank bank, CommandValidator commandValidator, CommandProcessor commandProcessor, CommandStorage commandStorage) {
        this.bank = bank;
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
    }

    public List<String> start(List<String> input) {
        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.processCommand(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        bank.getAccounts().forEach((key, account) -> {
            System.out.println(account.stateOfAccount());
            for (int i = 0; i < account.getTransactionHistory().size(); i++) {
                System.out.println(account.getTransactionHistory().get(i));
            }
        });
        for (int i = 0; i < commandStorage.getInvalidCommands().size(); i++) {
            System.out.println(commandStorage.getInvalidCommands().get(i));
        }
        return commandStorage.getInvalidCommands();
    }
}
