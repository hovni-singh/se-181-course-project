public class DepositCommandProcessor extends CommandProcessor {
    int id;
    int amount;

    public DepositCommandProcessor(Bank bank) {
        super(bank);
    }

    public void depositProcess(String[] commandSplit) {
        getDepositVariables(commandSplit);
        bank.getAccounts().get(id).deposit(amount);
    }

    private void getDepositVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }
}
