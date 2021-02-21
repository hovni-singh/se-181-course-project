public class DepositValidator {
    Bank bank;
    private int id;
    private int amount;

    public DepositValidator(Bank bank) {
        this.bank = bank;
    }

    public static boolean IsDigits(String s) {
        boolean numeric = true;
        try {
            Double num = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            numeric = false;
        }
        return numeric;
    }

    public boolean depositIsValid(String command) {
        String[] commandSplit = splitString(command);
        getVariables(commandSplit);
        if ((commandSplit[0].equalsIgnoreCase(("deposit"))) && ((commandSplit.length == 3))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean depositId(String command) {
        String[] commandSplit = splitString(command);
        getVariables(commandSplit);
        if (commandSplit[1].isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean depositAmount(String command) {
        String[] commandSplit = splitString(command);
        getVariables(commandSplit);
        if (commandSplit[2].isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean amountIsValid(String command) {
        String[] commandSplit = splitString(command);
        if (IsDigits(commandSplit[2]) == false) {
            return false;
        }
        getVariables(commandSplit);
        if (amount <= 0) {
            return false;
        } else if ((bank.getAccounts().get(id).getType().equalsIgnoreCase("Checking")) && amount > 1000) {
            return false;
        } else if ((bank.getAccounts().get(id).getType().equalsIgnoreCase("savings")) && amount > 2500) {
            return false;
        } else if (bank.getAccounts().get(id).getType().equalsIgnoreCase("cd")) {
            return false;
        } else {
            return true;
        }
    }

    private String[] splitString(String command) {
        String commandSplit[] = command.split(" ");
        return commandSplit;
    }

    private void getVariables(String[] commandSplit) {
        id = Integer.parseInt(commandSplit[1]);
        amount = Integer.parseInt(commandSplit[2]);
    }

}
