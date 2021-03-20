package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {
    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        bank.addCDAccount("CD", 27382367, 0.04, 300);
    }

    @Test
    public void transfer_command_is_valid() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 300");
        assertTrue(actual);
    }

    @Test
    public void typo_in_command() {
        boolean actual = commandValidator.validate("trensfer 13748594 23746537 600");
        assertFalse(actual);
    }

    @Test
    public void invalid_id() {
        boolean actual = commandValidator.validate("transfer 13748594 2746a37 600");
        assertFalse(actual);
    }


    @Test
    public void amount_is_valid() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 300");
        assertTrue(actual);
    }

    @Test
    public void cd_account_invalid() {
        boolean actual = commandValidator.validate("transfer 13748594 27382367 300");
        assertFalse(actual);
    }

    @Test
    public void character_in_amount_is_invalid() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 a00");
        assertFalse(actual);
    }

    @Test
    public void negative_amount_is_invalid() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 -600");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_is_invalid() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 0");
        assertFalse(actual);
    }

    @Test
    public void from_id_invalid_withdraw_amount() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 2000");
        assertFalse(actual);
    }

    @Test
    public void to_id_invalid_deposit_amount() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 2000");
        assertFalse(actual);
    }

    @Test
    public void checking_withdraw_amount_invalid() {
        boolean actual = commandValidator.validate("transfer 13748594 23746537 500");
        assertFalse(actual);
    }


    @Test
    public void transfer_from_savings_to_checking_before_month_passed_invalid() {
        boolean actual = commandValidator.validate("transfer 23746537 13748594 500");
        assertFalse(actual);
    }

    @Test
    public void transfer_savings_to_checking_after_month_passed() {
        bank.getAccounts().get(23746537).increaseMonth(1);
        boolean actual = commandValidator.validate("transfer 23746537 13748594 500");
        assertTrue(actual);
    }

    @Test
    public void transfer_between_same_account_invalid() {
        boolean actual = commandValidator.validate("transfer 13748594 13748594 2000");
        assertFalse(actual);
    }

}
