package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidatorTest {
    Bank bank;
    CommandValidator commandValidator;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        bank.addCDAccount("CD", 27382367, 0.04, 300);
    }

    @Test
    public void pass_command_is_valid() {
        boolean actual = commandValidator.validate("Pass 12");
        assertTrue(actual);
    }

    @Test
    public void typo_in_command() {
        boolean actual = commandValidator.validate("Pyss 12");
        assertFalse(actual);
    }

    @Test
    public void no_month_parameter_invalid() {
        boolean actual = commandValidator.validate("pass");
        assertFalse(actual);
    }


    @Test
    public void month_is_positive_integer() {
        boolean actual = commandValidator.validate("Pass 20");
        assertTrue(actual);
    }

    @Test
    public void zero_month_is_invalid() {
        boolean actual = commandValidator.validate("Pass 0");
        assertFalse(actual);
    }

    @Test
    public void month_above_60_is_invalid() {
        boolean actual = commandValidator.validate("Pass 70");
        assertFalse(actual);
    }

    @Test
    public void negative_month_is_invalid() {
        boolean actual = commandValidator.validate("Pass -4");
        assertFalse(actual);
    }

    @Test
    public void character_in_month_is_invalid() {
        boolean actual = commandValidator.validate("Pass a8");
        assertFalse(actual);
    }

}
