package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PassTimeCommandProcessorTest {

    Bank bank;
    CommandProcessor commandProcessor;

    private static double roundDouble(double d, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.20);
        bank.addSavingsAccount("Savings", 23746537, 0.80);
        bank.addCDAccount("CD", 27382367, 0.50, 600);
    }

    @Test
    public void increase_month_by_one() {
        commandProcessor.processCommand("deposit 13748594 500");
        commandProcessor.processCommand("pass 1");
        assertEquals(1, bank.getAccounts().get(13748594).getTime());
    }

    @Test
    public void account_with_balance_zero_after_one_month_is_closed() {
        commandProcessor.processCommand("pass 1");
        assertFalse(bank.accountExists(23746537));
    }

    @Test
    public void increase_month_by_three() {
        commandProcessor.processCommand("deposit 13748594 500");
        commandProcessor.processCommand("pass 3");
        assertEquals(3, bank.getAccounts().get(13748594).getTime());
    }

    @Test
    public void increase_month_twice() {
        commandProcessor.processCommand("deposit 13748594 500");
        commandProcessor.processCommand("pass 1");
        commandProcessor.processCommand("pass 1");
        assertEquals(2, bank.getAccounts().get(13748594).getTime());
    }

    @Test
    public void balance_increases_after_one_month() {
        commandProcessor.processCommand("deposit 13748594 500");
        commandProcessor.processCommand("pass 1");
        double balance = roundDouble(bank.getAccounts().get(13748594).getBalance(), 3);
        assertEquals(500.083, balance);
    }

    @Test
    public void balance_increases_after_three_months() {
        commandProcessor.processCommand("deposit 23746537 600");
        commandProcessor.processCommand("pass 3");
        double balance = roundDouble(bank.getAccounts().get(23746537).getBalance(), 3);
        assertEquals(601.201, balance);
    }

    @Test
    public void account_below_100_after_one_month() {
        commandProcessor.processCommand("deposit 13748594 40");
        commandProcessor.processCommand("pass 1");
        double balance = roundDouble(bank.getAccounts().get(13748594).getBalance(), 3);
        assertEquals(15.003, balance);
    }

    @Test
    public void account_below_100_for_two_months() {
        commandProcessor.processCommand("deposit 13748594 60");
        commandProcessor.processCommand("pass 2");
        double balance = roundDouble(bank.getAccounts().get(13748594).getBalance(), 3);
        assertEquals(10.008, balance);

    }

    @Test
    public void account_with_balance_then_no_balance_closes_after_two_months() {
        commandProcessor.processCommand("deposit 13748594 25");
        commandProcessor.processCommand("pass 2");
        assertFalse(bank.accountExists(13748594));
    }

    @Test
    public void two_different_accounts_closes_after_one_month_with_balance_zero() {
        commandProcessor.processCommand("pass 1");
        assertFalse(bank.accountExists(13748594));
        assertFalse(bank.accountExists(23746537));
    }

    @Test
    public void cd_account_balance_after_one_month() {
        commandProcessor.processCommand("pass 1");
        double balance = roundDouble(bank.getAccounts().get(27382367).getBalance(), 3);
        assertEquals(601.001, balance);
    }

    @Test
    public void cd_account_balance_after_two_months() {
        commandProcessor.processCommand("pass 2");
        double balance = roundDouble(bank.getAccounts().get(27382367).getBalance(), 3);
        assertEquals(602.004, balance);
    }

    @Test
    public void two_different_types_of_accounts_after_two_months() {
        commandProcessor.processCommand("deposit 13748594 500");
        commandProcessor.processCommand("pass 2");
        double balance = roundDouble(bank.getAccounts().get(27382367).getBalance(), 3);
        double balance2 = roundDouble(bank.getAccounts().get(13748594).getBalance(), 3);
        assertEquals(500.167, balance2);
        assertEquals(602.004, balance);
    }

}
