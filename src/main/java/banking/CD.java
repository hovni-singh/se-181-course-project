package banking;

public class CD extends Account {
    private double balance;

    public CD(String type, int id, double apr, int balance) {
        super(type, id, apr);
        this.balance = balance;
    }

    @Override
    boolean depositAmountIsTooGreat(double amount) {
        return true;
    }

    @Override
    boolean withdrawAmountIsTooGreat(int amount) {
        return false;
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
    public double withdraw(int amount) {
        if (super.getTime() >= 12) {
            balance = 0;
        }
        return balance;
    }
}
