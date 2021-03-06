package banking;

public class CommandProcessor {
    Bank bank;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
        String[] commandSplit = splitString(command);
        if (commandSplit[0].equalsIgnoreCase("create")) {
            createCommandProcessor.createProcess(commandSplit);
        }
        if (commandSplit[0].equalsIgnoreCase("deposit")) {
            depositCommandProcessor.depositProcess(commandSplit);
        }
    }

    private String[] splitString(String command) {
        String commandSplit[] = command.split(" ");
        return commandSplit;
    }
}
