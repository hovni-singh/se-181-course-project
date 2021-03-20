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
                    if (account.getBalance() <= 0) {
                        closeAccounts.add(account.getId());
                    }
                    if (account.getBalance() < 100) {
                        account.withdraw(25);
                    }
                    double apr = account.getAPR() / 100;
                    double rate = apr / 12;
                    double balance = account.getBalance();
                    if (account.getType().equalsIgnoreCase("cd")) {
                        for (int i = 0; i < 4; i++) {
                            sum += balance * rate;
                            balance += sum;
                        }
                    } else {
                        sum = balance * rate;
                    }
                    account.deposit(sum);
                });
                iteration++;
            }
            for (Integer closeAccount : closeAccounts) {
                bank.close(closeAccount);
            }
        }
    }
}
