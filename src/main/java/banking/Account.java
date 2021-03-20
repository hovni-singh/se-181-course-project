package banking;

import java.util.ArrayList;
import java.util.List;

abstract class Account {
    private final String type;
    private final int id;
    private final double apr;
    public List<String> transactionHistory;
    protected boolean hasMonthPassed = false;
    private int time;

    public Account(String type, int id, double apr) {
        this.type = type;
        this.id = id;
        this.apr = apr;
        time = 0;
        transactionHistory = new ArrayList<>();
    }

    static public String firstLetterCaps(String data) {
        String firstLetter = data.substring(0, 1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public double getAPR() {
        return apr;
    }

    public boolean getMonthPassed() {
        return hasMonthPassed;
    }

    public void increaseMonth(int month) {
        time += month;
        hasMonthPassed = true;
    }

    public int getTime() {
        return time;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransactionHistory(String output) {
        transactionHistory.add(output);
    }

    public String stateOfAccount() {
        String newApr = String.format("%.2f", getAPR());
        String balance = String.format("%.2f", getBalance());
        return firstLetterCaps(getType()) + " " + getId() + " " + balance + " " + newApr;
    }

    abstract boolean depositAmountIsTooGreat(double amount);

    abstract boolean withdrawAmountIsTooGreat(int amount);

    abstract public double getBalance();

    abstract public void deposit(double amount);

    abstract public void withdraw(int amount);

}

