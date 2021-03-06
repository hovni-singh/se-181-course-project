package banking;

public class CD extends Account {
    private String type;
    private int balance;

    public CD(String type, int id, double apr, int balance) {
        super(type, id, apr);
        this.type = "banking.CD";
        this.balance = balance;
    }

    @Override
    boolean amountIsTooGreat(int amount) {
        return false;
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
    public int withdraw(int amount) {
        if ((balance - amount) > 0) {
            balance -= amount;
        } else {
            balance = 0;
        }
        return balance;
    }
}
