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

    public boolean isAmountTooGreat(int id, int amount) {
        if (accounts.get(id).getType().equalsIgnoreCase("Checking")) {
            return getAccounts().get(id).amountIsTooGreat(amount);
        } else if (accounts.get(id).getType().equalsIgnoreCase("Savings")) {
            return getAccounts().get(id).amountIsTooGreat(amount);
        } else if (accounts.get(id).getType().equalsIgnoreCase("cd")) {
            return true;
        } else {
            return false;
        }
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