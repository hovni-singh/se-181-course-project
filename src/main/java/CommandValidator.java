public class CommandValidator {

    public static boolean IsDigits(String s) {
        boolean numeric = true;
        try {
            Double num = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            numeric = false;
        }
        return numeric;
    }


    public boolean accountTypeIsValid(String type) {
        if (type.isEmpty()) {
            return false;
        } else if (type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("savings") || type.equalsIgnoreCase("cd")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean idIsValid(int id) {
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

    public boolean checkForDuplicateId(int id, Bank bank) {
        if (bank.accountExists(id)) {
            return true;
        } else {
            return false;
        }
    }

    public String[] splitString(String command) {
        String commandSplit[] = command.split(" ");
        return commandSplit;
    }

}
