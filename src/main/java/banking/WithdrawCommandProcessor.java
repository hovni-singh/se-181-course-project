package banking;

public class WithdrawCommandProcessor extends CommandProcessor {
    int id;
    int amount;

    public WithdrawCommandProcessor(Bank bank) {
        super(bank);
    }

    public void withdrawProcess(String[] commandSplit) {
        getWithdrawVariables(commandSplit);
        bank.getAccounts().get(id).withdraw(amount);
    }

    private void getWithdrawVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }
}
