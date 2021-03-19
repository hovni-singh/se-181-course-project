package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidatorTest {
    WithdrawValidator withdrawValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        withdrawValidator = new WithdrawValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
    }

    @Test
    public void withdraw_command_is_valid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 600");
        assertTrue(actual);
    }

    @Test
    public void typo_in_withdraw() {
        boolean actual = withdrawValidator.withdrawValidate("withdeaw 23746537 600");
        assertFalse(actual);
    }

    @Test
    public void id_in_withdraw_command_is_invalid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 238a9 600");
        assertFalse(actual);
    }


    @Test
    public void amount_in_command() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 700");
        assertTrue(actual);
    }

    @Test
    public void amount_is_positive_integer() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 300");
        assertTrue(actual);
    }

    @Test
    public void negative_amount_is_invalid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 -300");
        assertFalse(actual);
    }

    @Test
    public void character_in_amount_is_invalid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 3a0");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_is_invalid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 0");
        assertFalse(actual);
    }

    @Test
    public void savings_amount_is_valid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 300");
        assertTrue(actual);
    }

    @Test
    public void amount_for_savings_is_above_maximum() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 23746537 2000");
        assertFalse(actual);
    }

    @Test
    public void checking_amount_is_valid() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 13748594 200");
        assertTrue(actual);
    }

    @Test
    public void amount_for_checking_is_above_maximum() {
        boolean actual = withdrawValidator.withdrawValidate("withdraw 13748594 600");
        assertFalse(actual);
    }
}
