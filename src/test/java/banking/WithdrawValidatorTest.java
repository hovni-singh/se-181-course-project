package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidatorTest {
    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        commandValidator = new CommandValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        bank.addCDAccount("CD", 20987654, 0.02, 400);
    }

    @Test
    public void withdraw_command_is_valid() {
        boolean actual = commandValidator.validate("withdraw 23746537 600");
        assertTrue(actual);
    }

    @Test
    public void typo_in_withdraw() {
        boolean actual = commandValidator.validate("withdeaw 23746537 600");
        assertFalse(actual);
    }

    @Test
    public void id_in_withdraw_command_is_invalid() {
        boolean actual = commandValidator.validate("withdraw 238a9 600");
        assertFalse(actual);
    }


    @Test
    public void amount_in_command() {
        boolean actual = commandValidator.validate("withdraw 23746537 700");
        assertTrue(actual);
    }

    @Test
    public void amount_is_positive_integer() {
        boolean actual = commandValidator.validate("withdraw 23746537 300");
        assertTrue(actual);
    }

    @Test
    public void negative_amount_is_invalid() {
        boolean actual = commandValidator.validate("withdraw 23746537 -300");
        assertFalse(actual);
    }

    @Test
    public void character_in_amount_is_invalid() {
        boolean actual = commandValidator.validate("withdraw 23746537 3a0");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_is_invalid() {
        boolean actual = commandValidator.validate("withdraw 23746537 0");
        assertFalse(actual);
    }

    @Test
    public void savings_amount_is_valid() {
        boolean actual = commandValidator.validate("withdraw 23746537 300");
        assertTrue(actual);
    }

    @Test
    public void amount_for_savings_is_above_maximum() {
        boolean actual = commandValidator.validate("withdraw 23746537 2000");
        assertFalse(actual);
    }

    @Test
    public void checking_amount_is_valid() {
        boolean actual = commandValidator.validate("withdraw 13748594 400");
        assertTrue(actual);
    }

    @Test
    public void amount_for_checking_is_above_maximum() {
        boolean actual = commandValidator.validate("withdraw 13748594 600");
        assertFalse(actual);
    }

    @Test
    public void withdraw_from_cd_before_12_months_invalid() {
        bank.getAccounts().get(20987654).increaseMonth(3);
        boolean actual = commandValidator.validate("withdraw 20987654 200");
        assertFalse(actual);
    }

    @Test
    public void withdrawing_from_cd_after_12_months() {
        bank.getAccounts().get(20987654).increaseMonth(12);
        boolean actual = commandValidator.validate("withdraw 20987654 400");
        assertTrue(actual);
    }

    @Test
    public void withdraw_from_savings_before_one_month_invalid() {
        boolean actual = commandValidator.validate("withdraw 20987654 400");
        assertFalse(actual);
    }

    @Test
    public void withdraw_from_savings_once_in_one_month() {

    }

    @Test
    public void withdrawing_from_cd_with_amount_more_than_balance() {
        bank.getAccounts().get(20987654).increaseMonth(12);
        boolean actual = commandValidator.validate("withdraw 20987654 500");
        assertTrue(actual);
    }

    @Test
    public void withdrawing_from_cd_with_less_than_the_balance() {
        bank.getAccounts().get(20987654).increaseMonth(12);
        boolean actual = commandValidator.validate("withdraw 20987654 100");
        assertFalse(actual);
    }
}
