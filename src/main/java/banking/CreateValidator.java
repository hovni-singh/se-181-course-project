package banking;

public class CreateValidator extends CommandValidator {
    String type;
    int id;
    double apr;
    int amount;

    public CreateValidator(Bank bank) {
        super(bank);
    }

    public boolean createValidate(String command) {
        String[] commandSplit = splitString(command);
        if (((commandSplit.length < 4) || (commandSplit.length > 5)) || (!(createIsValid(commandSplit))) || (!IsDigits(commandSplit[2])) || (!IsDigits(commandSplit[3]))) {
            return false;
        }
        getVariables(commandSplit);
        boolean check;
        if (checkForDuplicateId(id, bank)) {
            return false;
        }
        if (commandSplit.length == 4) {
            check = (accountTypeIsValid(type)) && (idIsValid(id)) && (aprIsValid(apr)) && (!type.equalsIgnoreCase("cd"));
        } else {
            check = (accountTypeIsValid(type)) && (idIsValid(id)) && (aprIsValid(apr)) && (cdAmountIsValid(command));
        }
        return check;
    }

    private boolean cdAmountIsValid(String command) {
        String[] commandSplit = splitString(command);
        if (commandSplit[4].isEmpty()) {
            return false;
        }
        amount = Integer.parseInt(commandSplit[4]);
        return amount >= 1000 && amount <= 10000;
    }

    private boolean createIsValid(String[] commandSplit) {
        return commandSplit[0].equalsIgnoreCase("create");
    }

    private boolean aprIsValid(double apr) {
        String[] split = Double.toString(apr).split("\\.");
        int decimalLength = split[1].length();
        if ((apr % 1 == 0) || (apr >= 10) || (apr <= 0)) {
            return false;
        }
        return decimalLength > 0;
    }

    private void getVariables(String[] commandSplit) {
        type = commandSplit[1];
        id = Integer.parseInt(commandSplit[2]);
        apr = Double.parseDouble(commandSplit[3]);
    }

}
