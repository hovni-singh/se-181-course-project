package banking;

public class Savings extends Account {
    private double balance = 0;

    public Savings(String type, int id, double apr) {
        super(type, id, apr);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean depositAmountIsTooGreat(double amount) {
        return amount > 2500;
    }


    @Override
    public boolean withdrawAmountIsTooGreat(int amount) {
        return amount > 1000;
    }

    @Override
    public void withdraw(int amount) {
        if ((balance - amount) >= 0) {
            balance -= amount;
        } else {
            balance = 0;
        }
        hasMonthPassed = false;
    }

}
