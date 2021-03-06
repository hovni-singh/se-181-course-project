public class CreateCommandProcessor extends CommandProcessor {

    String type;
    int id;
    double apr;
    int amount;

    public CreateCommandProcessor(Bank bank) {
        super(bank);
    }

    public void createProcess(String[] commandSplit) {
        getCreateVariables(commandSplit);
        if (commandSplit.length == 4) {
            isChecking();
            isSavings();
        }
        if (commandSplit.length == 5) {
            isCD(commandSplit);
        }

    }

    private void isCD(String[] commandSplit) {
        if (type.equalsIgnoreCase("cd")) {
            amount = Integer.parseInt(commandSplit[4]);
            bank.addCDAccount(type, id, apr, amount);
        }
    }

    private void isSavings() {
        if (type.equalsIgnoreCase("savings")) {
            bank.addSavingsAccount(type, id, apr);
        }
    }

    private void isChecking() {
        if (type.equalsIgnoreCase("checking")) {
            bank.addCheckingAccount(type, id, apr);
        }
    }


    private void getCreateVariables(String[] commandSplit) {
        type = commandSplit[1];
        id = Integer.parseInt(commandSplit[2]);
        apr = Double.parseDouble(commandSplit[3]);
    }


}
