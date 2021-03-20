package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
        List<String> outputList = new ArrayList<>();
        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.processCommand(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }
        Set<Integer> keys = bank.getAccounts().keySet();
        for (Integer k : keys) {
            outputList.add(bank.getAccounts().get(k).stateOfAccount());
            outputList.addAll(bank.getAccounts().get(k).getTransactionHistory());
        }
        outputList.addAll(commandStorage.getInvalidCommands());
        return outputList;
    }
}
