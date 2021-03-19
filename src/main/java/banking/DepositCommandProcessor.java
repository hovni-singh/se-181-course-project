package banking;

public class DepositCommandProcessor extends CommandProcessor {
    int id;
    double amount;

    public DepositCommandProcessor(Bank bank) {
        super(bank);
    }

    public void depositProcess(String[] commandSplit) {
        getDepositVariables(commandSplit);
        bank.getAccounts().get(id).deposit(amount);
    }

    private void getDepositVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Double.parseDouble(commandSplit[2]);
    }
}
