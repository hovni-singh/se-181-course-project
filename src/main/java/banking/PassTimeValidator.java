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
        return (month >= 1) && (month <= 60);
    }

    private boolean passIsValid(String[] commandSplit) {
        return (commandSplit[0].equalsIgnoreCase("pass")) && (commandSplit.length == 2);
    }


}
