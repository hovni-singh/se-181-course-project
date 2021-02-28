public class CommandProcessor {
    Bank bank;
    private String type;
    private int id;
    private double apr;
    private int amount;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        String[] commandSplit = splitString(command);
        if (commandSplit[0].equalsIgnoreCase("create")) {
            getCreateVariables(commandSplit);
            if (commandSplit.length == 4) {
                if (type.equalsIgnoreCase("checking")) {
                    bank.addCheckingAccount(type, id, apr);
                }
                if (type.equalsIgnoreCase("savings")) {
                    bank.addSavingsAccount(type, id, apr);
                }
            }
            if (commandSplit.length == 5) {
                if (type.equalsIgnoreCase("cd")) {
                    amount = Integer.parseInt(commandSplit[4]);
                    bank.addCDAccount(type, id, apr, amount);
                }
            }
        }
        if (commandSplit[0].equalsIgnoreCase("deposit")) {
            getDepositVariables(commandSplit);
            bank.getAccounts().get(id).deposit(amount);
        }
    }

    private String[] splitString(String command) {
        String commandSplit[] = command.split(" ");
        return commandSplit;
    }

    private void getCreateVariables(String[] commandSplit) {
        type = commandSplit[1];
        id = Integer.parseInt(commandSplit[2]);
        apr = Double.parseDouble(commandSplit[3]);
    }

    private void getDepositVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }
}
