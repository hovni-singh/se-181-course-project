package banking;

abstract class Account {
    private final String type;
    private final int id;
    private final double apr;

    public Account(String type, int id, double apr) {
        this.type = type;
        this.id = id;
        this.apr = apr;
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

    abstract boolean depositAmountIsTooGreat(int amount);

    abstract boolean withdrawAmountIsTooGreat(int amount);

    abstract public int getBalance();

    abstract public int deposit(int amount);

    abstract public int withdraw(int amount);

}

