package banking;

public class WithdrawValidator extends CommandValidator {

    private int id;
    private int amount;

    public WithdrawValidator(Bank bank) {
        super(bank);
    }

    public boolean withdrawValidate(String command) {
        String[] commandSplit = splitString(command);
        if ((!(withdrawIsValid(commandSplit))) || (IsDigits(commandSplit[1])) || (IsDigits(commandSplit[2]))) {
            return false;
        }
        getVariables(commandSplit);
        if ((bank.getAccounts().get(id).getType().equalsIgnoreCase("savings")) && (!checkMonth())) {
            return false;
        }
        if (amount <= 0 || !bank.accountExists(id) || (cdCheck()) || bank.withdrawAmountTooGreat(id, amount)) {
            return false;
        }
        return idIsValid(id);
    }

    private boolean checkMonth() {
        return bank.getAccounts().get(id).getMonthPassed();
    }

    private boolean cdCheck() {
        if (bank.getAccounts().get(id).getType().equalsIgnoreCase("cd")) {
            return (bank.getAccounts().get(id).getTime() < 12) || (amount < bank.getAccounts().get(id).getBalance());
        }
        return false;
    }

    private boolean withdrawIsValid(String[] commandSplit) {
        return (commandSplit[0].equalsIgnoreCase(("withdraw"))) && ((commandSplit.length == 3));
    }

    private void getVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }
}
