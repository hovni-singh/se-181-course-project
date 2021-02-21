public class CreateValidator {
    Bank bank;
    private String type;
    private int id;
    private double apr;
    private int amount;

    public CreateValidator(Bank bank) {
        this.bank = bank;
    }

    public static boolean IsDigits(String id) {
        boolean numeric = true;
        try {
            Double num = Double.parseDouble(id);
        } catch (NumberFormatException nfe) {
            numeric = false;
        }
        return numeric;
    }

    public boolean createIsValid(String command) {
        String[] commandSplit = splitString(command);
        getVariables(commandSplit);
        if ((commandSplit[0].equalsIgnoreCase(("create"))) && ((commandSplit.length == 3) || (commandSplit.length == 4))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean accountTypeIsValid(String command) {
        String[] commandSplit = splitString(command);
        getVariables(commandSplit);
        if (type.isEmpty()) {
            return false;
        } else if (type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("savings") || type.equalsIgnoreCase("cd")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean idIsValid(String command) {
        String[] commandSplit = splitString(command);
        if ((commandSplit[2].isEmpty()) || (IsDigits(commandSplit[2]) == false)) {
            return false;
        }
        getVariables(commandSplit);
        int count = 0;
        while (id != 0) {
            id /= 10;
            ++count;
        }
        if (count == 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkForDuplicateId(String command) {
        String[] commandSplit = splitString(command);
        getVariables(commandSplit);
        if (bank.accountExists(id)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean aprIsValid(String command) {
        String[] commandSplit = splitString(command);
        if ((commandSplit[3].isEmpty()) || (IsDigits(commandSplit[3]) == false)) {
            return false;
        }
        getVariables(commandSplit);
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

    public boolean cdAmountIsValid(String command) {
        String[] commandSplit = splitString(command);
        boolean check;
        amount = Integer.parseInt(commandSplit[4]);
        if (commandSplit[1].equalsIgnoreCase("cd") && (commandSplit.length == 4)) {
            check = true;
        } else {
            check = false;
        }
        if (amount >= 1000 && amount <= 10000) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }


    private String[] splitString(String command) {
        String commandSplit[] = command.split(" ");
        return commandSplit;
    }

    private void getVariables(String[] commandSplit) {
        type = commandSplit[1];
        id = Integer.parseInt(commandSplit[2]);
        apr = Double.parseDouble(commandSplit[3]);
    }

}
