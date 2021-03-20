package banking;

import java.util.ArrayList;
import java.util.List;

public class PassTimeCommandProcessor extends CommandProcessor {

    int month;
    double sum;

    public PassTimeCommandProcessor(Bank bank) {
        super(bank);
    }

    public void passProcess(String[] commandSplit) {
        month = Integer.parseInt(commandSplit[1]);
        List<Integer> closeAccounts = new ArrayList<>();
        int iteration = 0;
        if (!bank.getAccounts().isEmpty()) {
            while (iteration < month) {
                bank.getAccounts().forEach((key, account) -> {
                    account.increaseMonth(1);
                    isBalanceZero(closeAccounts, account);
                    isBalanceLessThan100(account);
                    double apr = account.getAPR() / 100;
                    double rate = apr / 12;
                    double balance = account.getBalance();
                    getSum(account, rate, balance);
                    account.deposit(sum);
                });
                iteration++;
            }
        }
        for (Integer closeAccount : closeAccounts) {
            bank.close(closeAccount);
        }
    }

    private void isBalanceLessThan100(Account account) {
        if (account.getBalance() < 100) {
            account.withdraw(25);
        }
    }

    private void isBalanceZero(List<Integer> closeAccounts, Account account) {
        if (account.getBalance() <= 0) {
            closeAccounts.add(account.getId());
        }
    }

    private void getSum(Account account, double rate, double balance) {
        if (account.getType().equalsIgnoreCase("cd")) {
            for (int i = 0; i < 4; i++) {
                sum += balance * rate;
                balance += sum;
            }
        } else {
            sum = balance * rate;
        }
    }
}
