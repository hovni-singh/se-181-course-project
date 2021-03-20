package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {
    CommandValidator commandValidator;
    Bank bank;


    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
    }

    @Test
    public void deposit_command_is_valid() {
        boolean actual = commandValidator.validate("deposit 23746537 200");
        assertTrue(actual);
    }

    @Test
    public void typo_in_deposit_command() {
        boolean actual = commandValidator.validate("depoit 23746537 200");
        assertFalse(actual);
    }

    @Test
    public void deposit_has_id() {
        boolean actual = commandValidator.validate("deposit 23746537 200");
        assertTrue(actual);
    }

    @Test
    public void deposit_has_amount() {
        boolean actual = commandValidator.validate("deposit 23746537 200");
        assertTrue(actual);
    }

    @Test
    public void amount_is_not_negative() {
        boolean actual = commandValidator.validate("deposit 23746537 400");
        assertTrue(actual);
    }

    @Test
    public void negative_amount_is_invalid() {
        boolean actual = commandValidator.validate("deposit 23746537 -400");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_is_invalid() {
        boolean actual = commandValidator.validate("deposit 23746537 0");
        assertFalse(actual);
    }

    @Test
    public void character_in_amount_is_invalid() {
        boolean actual = commandValidator.validate("deposit 23746537 67hj");
        assertFalse(actual);
    }

    @Test
    public void valid_savings_deposit() {
        boolean actual = commandValidator.validate("deposit 23746537 2000");
        assertTrue(actual);
    }

    @Test
    public void savings_amount_greater_than_maximum_is_invalid() {
        boolean actual = commandValidator.validate("deposit 23746537 3000");
        assertFalse(actual);
    }

    @Test
    public void savings_amount_at_maximum() {
        boolean actual = commandValidator.validate("deposit 23746537 2500");
        assertTrue(actual);
    }

    @Test
    public void valid_checking_deposit() {
        boolean actual = commandValidator.validate("deposit 13748594 500");
        assertTrue(actual);
    }

    @Test
    public void checking_amount_greater_than_maximum_is_invalid() {
        boolean actual = commandValidator.validate("deposit 13748594 2000");
        assertFalse(actual);
    }

    @Test
    public void checking_amount_at_maximum() {
        boolean actual = commandValidator.validate("deposit 13748594 1000");
        assertTrue(actual);
    }

    @Test
    public void deposit_into_cd_is_invalid() {
        bank.addCDAccount("cd", 23746537, 0.02, 200);
        boolean actual = commandValidator.validate("deposit 23746537 400");
        assertFalse(actual);
    }

}
