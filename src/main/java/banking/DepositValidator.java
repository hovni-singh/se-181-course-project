package banking;

public class DepositValidator extends CommandValidator {
    Bank bank;
    private int id;
    private double amount;

    public DepositValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean depositValidate(String command) {
        String[] commandSplit = splitString(command);
        if ((commandSplit.length != 3)) {
            return false;
        }
        if (!(depositIsValid(commandSplit))) {
            return false;
        }
        if ((IsDigits(commandSplit[1]) == false)) {
            return false;
        }
        if ((IsDigits(commandSplit[2]) == false)) {
            return false;
        }
        getVariables(commandSplit);
        if ((bank.depositAmountTooGreat(id, amount)) || amount <= 0 || bank.getAccounts().get(id).getType().equalsIgnoreCase("cd")) {
            return false;
        }
        if (idIsValid(id)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean depositIsValid(String[] commandSplit) {
        if ((commandSplit[0].equalsIgnoreCase(("deposit"))) && ((commandSplit.length == 3))) {
            return true;
        } else {
            return false;
        }
    }


    private void getVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Double.parseDouble(commandSplit[2]);
    }

}
