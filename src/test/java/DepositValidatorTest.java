import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {
    DepositValidator depositValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        depositValidator = new DepositValidator(bank);
    }

    @Test
    public void deposit_command_is_valid() {
        boolean actual = depositValidator.depositIsValid("deposit 23746537 200");
        assertTrue(actual);
    }

    @Test
    public void typo_in_deposit_command() {
        boolean actual = depositValidator.depositIsValid("depoit 23746537 200");
        assertFalse(actual);
    }

    @Test
    public void deposit_has_id() {
        boolean actual = depositValidator.depositId("deposit 23746537 200");
        assertTrue(actual);
    }

    @Test
    public void deposit_has_amount() {
        boolean actual = depositValidator.depositAmount("deposit 23746537 200");
        assertTrue(actual);
    }

    @Test
    public void amount_is_not_negative() {
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        boolean actual = depositValidator.amountIsValid("deposit 23746537 400");
        assertTrue(actual);
    }

    @Test
    public void negative_amount_is_invalid() {
        boolean actual = depositValidator.amountIsValid("deposit 23746537 -400");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_is_invalid() {
        boolean actual = depositValidator.amountIsValid("deposit 23746537 0");
        assertFalse(actual);
    }

    @Test
    public void character_in_amount_is_invalid() {
        boolean actual = depositValidator.amountIsValid("deposit 23746537 67hj");
        assertFalse(actual);
    }

    @Test
    public void valid_savings_deposit() {
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        boolean actual = depositValidator.amountIsValid("deposit 23746537 2000");
        assertTrue(actual);
    }

    @Test
    public void savings_amount_greater_than_maximum_is_invalid() {
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        boolean actual = depositValidator.amountIsValid("deposit 23746537 3000");
        assertFalse(actual);
    }

    @Test
    public void valid_checking_deposit() {
        bank.addCheckingAccount("Checking", 23746537, 0.02);
        boolean actual = depositValidator.amountIsValid("deposit 23746537 500");
        assertTrue(actual);
    }

    @Test
    public void checking_amount_greater_than_maximum_is_invalid() {
        bank.addCheckingAccount("Checking", 23746537, 0.02);
        boolean actual = depositValidator.amountIsValid("deposit 23746537 2000");
        assertFalse(actual);
    }

    @Test
    public void deposit_into_cd_is_invalid() {
        bank.addCDAccount("cd", 23746537, 0.02, 200);
        boolean actual = depositValidator.amountIsValid("deposit 23746537 400");
        assertFalse(actual);
    }

}
