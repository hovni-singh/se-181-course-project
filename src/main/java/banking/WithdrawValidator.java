package banking;

public class WithdrawValidator extends CommandValidator {

    private int id;
    private int amount;

    public WithdrawValidator(Bank bank) {
        super(bank);
    }

    public boolean withdrawValidate(String command) {
        String[] commandSplit = splitString(command);
        if ((!(withdrawIsValid(commandSplit))) || (IsDigits(commandSplit[1]) == false) || (IsDigits(commandSplit[2]) == false)) {
            return false;
        }
        getVariables(commandSplit);
        if (amount <= 0 || !bank.accountExists(id) || (cdCheck()) || bank.withdrawAmountTooGreat(id, amount)) {
            return false;
        }
        if (idIsValid(id)) {
            return true;
        } else {
            return false;
        }
    }


    private boolean cdCheck() {
        if (bank.getAccounts().get(id).getType().equalsIgnoreCase("cd")) {
            if ((bank.getAccounts().get(id).getTime() < 12) || (amount < bank.getAccounts().get(id).getBalance())) {
                return true;
            }
        }
        return false;
    }

    private boolean withdrawIsValid(String[] commandSplit) {
        if ((commandSplit[0].equalsIgnoreCase(("withdraw"))) && ((commandSplit.length == 3))) {
            return true;
        } else {
            return false;
        }
    }

    private void getVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }
}
