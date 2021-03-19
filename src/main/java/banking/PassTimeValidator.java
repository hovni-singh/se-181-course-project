package banking;

public class PassTimeValidator extends CommandValidator {
    int month;

    public PassTimeValidator(Bank bank) {
        super(bank);
    }

    public boolean passValidate(String command) {
        String[] commandSplit = splitString(command);
        if ((!passIsValid(commandSplit)) || (!IsDigits(commandSplit[1]))) {
            return false;
        }
        month = Integer.parseInt(commandSplit[1]);
        if ((month < 1) || (month > 60)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean passIsValid(String[] commandSplit) {
        if ((commandSplit[0].equalsIgnoreCase("pass")) && (commandSplit.length == 2)) {
            return true;
        } else {
            return false;
        }
    }


}
