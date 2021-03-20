package banking;

public class DepositValidator extends CommandValidator {
    private int id;
    private double amount;

    public DepositValidator(Bank bank) {
        super(bank);
    }

    public boolean depositValidate(String command) {
        String[] commandSplit = splitString(command);
        if (((commandSplit.length != 3)) || (!(depositIsValid(commandSplit))) || (!IsDigits(commandSplit[1])) || (!IsDigits(commandSplit[2]))) {
            return false;
        }
        getVariables(commandSplit);
        if ((bank.depositAmountTooGreat(id, amount)) || !bank.accountExists(id) || amount <= 0 || bank.getAccounts().get(id).getType().equalsIgnoreCase("cd")) {
            return false;
        }
        return idIsValid(id);
    }

    private boolean depositIsValid(String[] commandSplit) {
        return (commandSplit[0].equalsIgnoreCase(("deposit"))) && ((commandSplit.length == 3));
    }


    private void getVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Double.parseDouble(commandSplit[2]);
    }

}
