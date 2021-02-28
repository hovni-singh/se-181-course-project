public class CreateValidator extends CommandValidator {
    Bank bank;
    String type;
    int id;
    double apr;
    int amount;

    public CreateValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean createValidate(String command) {
        String[] commandSplit = splitString(command);
        if ((commandSplit.length < 4) || (commandSplit.length > 5)) {
            return false;
        }
        if (!(createIsValid(commandSplit))) {
            return false;
        }
        if ((IsDigits(commandSplit[2]) == false)) {
            return false;
        }
        if ((IsDigits(commandSplit[3]) == false)) {
            return false;
        }
        getVariables(commandSplit);
        boolean check;
        if (checkForDuplicateId(id, bank)) {
            return false;
        }
        if (commandSplit.length == 4) {
            if ((accountTypeIsValid(type)) && (idIsValid(id)) && (aprIsValid(apr))) {
                check = true;
            } else {
                check = false;
            }
        } else {
            if ((accountTypeIsValid(type)) && (idIsValid(id)) && (aprIsValid(apr)) && (cdAmountIsValid(command))) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    private boolean cdAmountIsValid(String command) {
        String[] commandSplit = splitString(command);
        amount = Integer.parseInt(commandSplit[4]);
        if (amount >= 1000 && amount <= 10000) {
            return true;
        } else {
            return false;
        }
    }

    private boolean createIsValid(String[] commandSplit) {
        if (commandSplit[0].equalsIgnoreCase("create")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean aprIsValid(double apr) {
        String[] split = Double.toString(apr).split("\\.");
        split[0].length();
        int decimalLength = split[1].length();
        if ((apr % 1 == 0) || (apr >= 1.00) || (apr <= 0)) {
            return false;
        }
        if (decimalLength == 2) {
            return true;
        } else {
            return false;
        }
    }

    private void getVariables(String[] commandSplit) {
        type = commandSplit[1];
        id = Integer.parseInt(commandSplit[2]);
        apr = Double.parseDouble(commandSplit[3]);
    }

}
