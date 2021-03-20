package banking;

public class TransferValidator extends CommandValidator {
    DepositValidator depositValidator;
    WithdrawValidator withdrawValidator;

    public TransferValidator(Bank bank) {
        super(bank);
    }

    public boolean transferValidate(String command) {
        depositValidator = new DepositValidator(bank);
        withdrawValidator = new WithdrawValidator(bank);
        String[] commandSplit = splitString(command);
        if ((!(transferIsValid(commandSplit))) || (commandSplit[1].equals(commandSplit[2])) || (!IsDigits(commandSplit[1])) || (!IsDigits(commandSplit[2]))) {
            return false;
        }
        if (checkForInvalidType(commandSplit)) {
            return false;
        }
        String depositString = "deposit " + commandSplit[2] + " " + commandSplit[3];
        String withdrawString = "withdraw " + commandSplit[1] + " " + commandSplit[3];
        if (depositValidator.depositValidate(depositString) && withdrawValidator.withdrawValidate(withdrawString)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkForInvalidType(String[] commandSplit) {
        int toId = Integer.parseInt(commandSplit[1]);
        int fromId = Integer.parseInt(commandSplit[2]);
        if ((bank.getAccounts().get(fromId).getType().equalsIgnoreCase("cd")) || !bank.accountExists(fromId) || !bank.accountExists(toId) || (bank.getAccounts().get(toId).getType().equalsIgnoreCase("cd"))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean transferIsValid(String[] commandSplit) {
        if ((commandSplit[0].equalsIgnoreCase("transfer")) && (commandSplit.length == 4)) {
            return true;
        } else {
            return false;
        }
    }

}
