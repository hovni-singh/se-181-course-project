package banking;

public class Checking extends Account {
    private String type;
    private int balance = 0;

    public Checking(String type, int id, double apr) {
        super(type, id, apr);
        this.type = "banking.Checking";
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    @Override
    public boolean depositAmountIsTooGreat(int amount) {
        if (amount > 1000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean withdrawAmountIsTooGreat(int amount) {
        if (amount > 400) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int withdraw(int amount) {
        if ((balance - amount) > 0) {
            balance -= amount;
        } else {
            balance = 0;
        }
        return balance;
    }
}
