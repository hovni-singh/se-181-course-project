package banking;

abstract class Account {
    private final String type;
    private final int id;
    private final double apr;
    private int time;

    public Account(String type, int id, double apr) {
        this.type = type;
        this.id = id;
        this.apr = apr;
        time = 0;
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

    public void increaseMonth(int month) {
        time += month;
    }

    public int getTime() {
        return time;
    }

    abstract boolean depositAmountIsTooGreat(double amount);

    abstract boolean withdrawAmountIsTooGreat(int amount);

    abstract public double getBalance();

    abstract public double deposit(double amount);

    abstract public double withdraw(int amount);

}

