package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Integer, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public boolean accountExists(int id) {
        if (accounts.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean depositAmountTooGreat(int id, double amount) {
        if (accounts.get(id).depositAmountIsTooGreat(amount)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean withdrawAmountTooGreat(int id, int amount) {
        if (accounts.get(id).withdrawAmountIsTooGreat(amount)) {
            return true;
        } else {
            return false;
        }
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