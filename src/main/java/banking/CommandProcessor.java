package banking;

public class CommandProcessor {
    Bank bank;
    CreateCommandProcessor createCommandProcessor;
    DepositCommandProcessor depositCommandProcessor;
    WithdrawCommandProcessor withdrawCommandProcessor;
    TransferCommandProcessor transferCommandProcessor;
    PassTimeCommandProcessor passTimeCommandProcessor;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        createCommandProcessor = new CreateCommandProcessor(bank);
        depositCommandProcessor = new DepositCommandProcessor(bank);
        withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
        transferCommandProcessor = new TransferCommandProcessor(bank);
        passTimeCommandProcessor = new PassTimeCommandProcessor(bank);
        String[] commandSplit = splitString(command);
        create(commandSplit);
        deposit(commandSplit);
        withdraw(commandSplit);
        transfer(commandSplit);
        pass(commandSplit);
    }

    private void pass(String[] commandSplit) {
        if (commandSplit[0].equalsIgnoreCase("pass")) {
            passTimeCommandProcessor.passProcess(commandSplit);
        }
    }

    private void transfer(String[] commandSplit) {
        if (commandSplit[0].equalsIgnoreCase("transfer")) {
            transferCommandProcessor.transferProcess(commandSplit);
        }
    }

    private void withdraw(String[] commandSplit) {
        if (commandSplit[0].equalsIgnoreCase("withdraw")) {
            withdrawCommandProcessor.withdrawProcess(commandSplit);
        }
    }

    private void deposit(String[] commandSplit) {
        if (commandSplit[0].equalsIgnoreCase("deposit")) {
            depositCommandProcessor.depositProcess(commandSplit);
        }
    }

    private void create(String[] commandSplit) {
        if (commandSplit[0].equalsIgnoreCase("create")) {
            createCommandProcessor.createProcess(commandSplit);
        }
    }

    private String[] splitString(String command) {
        return command.split(" ");
    }
}
