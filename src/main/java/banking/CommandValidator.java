package banking;

public class CommandValidator {
    Bank bank;
    CreateValidator createValidator;
    DepositValidator depositValidator;
    WithdrawValidator withdrawValidator;
    TransferValidator transferValidator;
    PassTimeValidator passTimeValidator;

    public CommandValidator(Bank bank) {
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

    public boolean validate(String command) {
        createValidator = new CreateValidator(bank);
        depositValidator = new DepositValidator(bank);
        withdrawValidator = new WithdrawValidator(bank);
        transferValidator = new TransferValidator(bank);
        passTimeValidator = new PassTimeValidator(bank);
        String[] commandSplit = splitString(command);
        if (commandSplit[0].equalsIgnoreCase("create")) {
            return createValidator.createValidate(command);
        }
        if (commandSplit[0].equalsIgnoreCase("deposit")) {
            return depositValidator.depositValidate(command);
        }
        if (commandSplit[0].equalsIgnoreCase("withdraw")) {
            return withdrawValidator.withdrawValidate(command);
        }
        if (commandSplit[0].equalsIgnoreCase("transfer")) {
            return transferValidator.transferValidate(command);
        }
        if (commandSplit[0].equalsIgnoreCase("pass")) {
            return passTimeValidator.passValidate(command);
        }
        return false;
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
