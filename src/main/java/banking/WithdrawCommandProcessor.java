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
        String request = "Withdraw " + commandSplit[1] + " " + commandSplit[2];
        bank.getAccounts().get(id).addTransactionHistory(request);
    }

    private void getWithdrawVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }
}
