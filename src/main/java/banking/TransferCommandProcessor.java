package banking;

public class TransferCommandProcessor extends CommandProcessor {
    int fromId;
    int toId;
    int amount;

    public TransferCommandProcessor(Bank bank) {
        super(bank);
    }

    public void transferProcess(String[] commandSplit) {
        getTransferVariables(commandSplit);
        bank.getAccounts().get(fromId).withdraw(amount);
        bank.getAccounts().get(toId).deposit(amount);
    }

    private void getTransferVariables(String[] commandSplit) {
        fromId = Integer.parseInt(commandSplit[1]);
        toId = Integer.parseInt(commandSplit[2]);
        amount = Integer.parseInt(commandSplit[3]);
    }
}
