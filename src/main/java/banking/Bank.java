package banking;

import java.util.LinkedHashMap;
import java.util.Map;


public class Bank {

    private final Map<Integer, Account> accounts;

    public Bank() {
        accounts = new LinkedHashMap<>();
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public boolean accountExists(int id) {
        return accounts.get(id) != null;
    }

    public boolean depositAmountTooGreat(int id, double amount) {
        return accounts.get(id).depositAmountIsTooGreat(amount);
    }

    public boolean withdrawAmountTooGreat(int id, int amount) {
        return accounts.get(id).withdrawAmountIsTooGreat(amount);
    }

    public void close(int id) {
        accounts.remove(id);
    }

    public void addSavingsAccount(String type, int id, double apr) {
        accounts.put(id, new Savings(type, id, apr));
    }

    public void addCheckingAccount(String type, int id, double apr) {
        accounts.put(id, new Checking(type, id, apr));
    }

    public void addCDAccount(String type, int id, double apr, int balance) {
        accounts.put(id, new CD(type, id, apr, balance));
    }


}