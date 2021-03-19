package banking;

public class CommandProcessor {
    Bank bank;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;
    WithdrawCommandProcessor withdrawCommandProcessor;
    TransferCommandProcessor transferCommandProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
        withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
        transferCommandProcessor = new TransferCommandProcessor(bank);
        String[] commandSplit = splitString(command);
        if (commandSplit[0].equalsIgnoreCase("create")) {
            createCommandProcessor.createProcess(commandSplit);
        }
        if (commandSplit[0].equalsIgnoreCase("deposit")) {
            depositCommandProcessor.depositProcess(commandSplit);
        }
        if (commandSplit[0].equalsIgnoreCase("withdraw")) {
            withdrawCommandProcessor.withdrawProcess(commandSplit);
        }
        if (commandSplit[0].equalsIgnoreCase("transfer")) {
            transferCommandProcessor.transferProcess(commandSplit);
        }
    }

    private String[] splitString(String command) {
        String commandSplit[] = command.split(" ");
        return commandSplit;
    }
}
