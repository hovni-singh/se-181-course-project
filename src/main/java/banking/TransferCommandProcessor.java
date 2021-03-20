package banking;

public class TransferCommandProcessor extends CommandProcessor {
    int fromId;
    int toId;
    double amount;

    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    public void transferProcess(String[] commandSplit) {
        getTransferVariables(commandSplit);
        bank.getAccounts().get(fromId).withdraw(Integer.parseInt(commandSplit[3]));
        bank.getAccounts().get(toId).deposit(amount);
        String request = "Transfer " + commandSplit[1] + " " + commandSplit[2] + " " + commandSplit[3];
        bank.getAccounts().get(fromId).addTransactionHistory(request);
        bank.getAccounts().get(toId).addTransactionHistory(request);
    }

    private void getTransferVariables(String[] commandSplit) {
        fromId = Integer.parseInt(commandSplit[1]);
        toId = Integer.parseInt(commandSplit[2]);
        amount = Double.parseDouble(commandSplit[3]);
    }
}
