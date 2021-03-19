package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {
    TransferValidator transferValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        transferValidator = new TransferValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        bank.addCDAccount("CD", 27382367, 0.04, 300);
    }

    @Test
    public void transfer_command_is_valid() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 600");
        assertTrue(actual);
    }

    @Test
    public void typo_in_command() {
        boolean actual = transferValidator.transferValidate("trensfer 23746537 13748594 600");
        assertFalse(actual);
    }

    @Test
    public void invalid_id() {
        boolean actual = transferValidator.transferValidate("transfer 2746a37 13748594 600");
        assertFalse(actual);
    }


    @Test
    public void amount_is_valid() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 500");
        assertTrue(actual);
    }

    @Test
    public void cd_account_invalid() {
        boolean actual = transferValidator.transferValidate("transfer 27382367 13748594 600");
        assertFalse(actual);
    }

    @Test
    public void character_in_amount_is_invalid() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 a00");
        assertFalse(actual);
    }

    @Test
    public void negative_amount_is_invalid() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 -600");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_is_invalid() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 0");
        assertFalse(actual);
    }

    @Test
    public void from_id_invalid_withdraw_amount() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 2000");
        assertFalse(actual);
    }

    @Test
    public void to_id_invalid_deposit_amount() {
        boolean actual = transferValidator.transferValidate("transfer 23746537 13748594 2000");
        assertFalse(actual);
    }

    @Test
    public void checking_withdraw_amount_invalid() {
        boolean actual = transferValidator.transferValidate("transfer 13748594 23746537 2000");
        assertFalse(actual);
    }

    @Test
    public void transfer_between_same_account_invalid() {
        boolean actual = transferValidator.transferValidate("transfer 13748594 13748594 2000");
        assertFalse(actual);
    }

}
