package banking;

public class Savings extends Account {
    private String type;
    private int balance = 0;

    public Savings(String type, int id, double apr) {
        super(type, id, apr);
        this.type = "banking.Savings";
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
    public boolean amountIsTooGreat(int amount) {
        if (amount > 2500) {
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
