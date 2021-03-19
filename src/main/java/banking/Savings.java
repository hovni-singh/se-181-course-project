package banking;

public class Savings extends Account {
    private String type;
    private double balance = 0;

    public Savings(String type, int id, double apr) {
        super(type, id, apr);
        this.type = "Savings";
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    @Override
    public boolean depositAmountIsTooGreat(double amount) {
        if (amount > 2500) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean withdrawAmountIsTooGreat(int amount) {
        if (amount > 1000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double withdraw(int amount) {
        if ((balance - amount) > 0) {
            balance -= amount;
        } else {
            balance = 0;
        }
        return balance;
    }

}
