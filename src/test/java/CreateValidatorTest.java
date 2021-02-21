import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidatorTest {
    CreateValidator createValidator;
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        createValidator = new CreateValidator(bank);
    }

    @Test
    public void create_command_is_valid() {
        boolean actual = createValidator.createIsValid("create checking 26374837 0.02");
        assertTrue(actual);
    }

    @Test
    public void typo_in_create_command() {
        boolean actual = createValidator.createIsValid("ceat checking 26374837 0.01");
        assertFalse(actual);
    }

    @Test
    public void create_has_account_type() {
        boolean actual = createValidator.accountTypeIsValid("create checking 26374837 0.01");
        assertTrue(actual);
    }

    @Test
    public void create_has_id() {
        boolean actual = createValidator.idIsValid("create checking 26374837 0.01");
        assertTrue(actual);
    }

    @Test
    public void create_has_apr() {
        boolean actual = createValidator.idIsValid("create checking 26374837 0.01");
        assertTrue(actual);
    }

    @Test
    public void account_type_is_valid() {
        boolean actual = createValidator.accountTypeIsValid("create checking 26374837 0.01");
        boolean actual1 = createValidator.accountTypeIsValid("create savings 26374837 0.01");
        boolean actual2 = createValidator.accountTypeIsValid("create cd 26374837 0.1 100");
        assertTrue(actual && actual1 && actual2);
    }

    @Test
    public void account_type_is_invalid() {
        boolean actual = createValidator.accountTypeIsValid("create fixed 26374837 0.01");
        assertFalse(actual);
    }

    @Test
    public void duplicate_id_is_invalid() {
        bank.addCheckingAccount("Checking", 67345637, 0.02);
        boolean actual = createValidator.checkForDuplicateId("create checking 67345637 0.02");
        assertFalse(actual);
    }

    @Test
    public void id_is_eight_digits() {
        boolean actual = createValidator.idIsValid("create checking 67345637 0.02");
        assertTrue(actual);
    }

    @Test
    public void id_is_not_eight_digits() {
        boolean actual = createValidator.idIsValid("create checking 3748 0.02");
        assertFalse(actual);

    }

    @Test
    public void id_is_not_integer() {
        boolean actual = createValidator.idIsValid("create checking 1274hyk9 0.02");
        assertFalse(actual);
    }

    @Test
    public void apr_is_percentage() {
        boolean actual = createValidator.aprIsValid("create checking 12789345 0.02");
        assertTrue(actual);
    }

    @Test
    public void integer_apr_is_invalid() {
        boolean actual = createValidator.aprIsValid("create checking 12789345 2");
        assertFalse(actual);
    }

    @Test
    public void apr_100_is_invalid() {
        boolean actual = createValidator.aprIsValid("create checking 12789345 1.00");
        assertFalse(actual);
    }

    @Test
    public void character_apr_is_invalid() {
        boolean actual = createValidator.aprIsValid("create checking 12789345 1.hf");
        assertFalse(actual);
    }


    @Test
    public void negative_apr_is_invalid() {
        boolean actual = createValidator.aprIsValid("create checking 12789345 -0.03");
        assertFalse(actual);
    }

    @Test
    public void zero_apr_is_invalid() {
        boolean actual = createValidator.aprIsValid("create checking 12789345 0");
        assertFalse(actual);
    }

    @Test
    public void cd_has_amount() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 1200");
        assertTrue(actual);
    }

    @Test
    public void negative_cd_is_invalid() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 -200");
        assertFalse(actual);
    }

    @Test
    public void zero_amount_for_cd_is_invalid() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 0");
        assertFalse(actual);
    }

    @Test
    public void minimum_amount_for_cd() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 1300");
        assertTrue(actual);
    }

    @Test
    public void amount_for_cd_below_minimum_is_invalid() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 300");
        assertFalse(actual);
    }

    @Test
    public void maximum_amount_for_cd() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 9000");
        assertTrue(actual);
    }

    @Test
    public void amount_for_cd_greater_than_max_is_invalid() {
        boolean actual = createValidator.cdAmountIsValid("create cd 12789345 0.04 13000");
        assertFalse(actual);
    }

    @Test
    public void create_command_is_case_insensitive() {
        boolean actual = createValidator.createIsValid("CrEaTe checking 23748576 0.03");
        boolean actual1 = createValidator.createIsValid("CrEaTe cheCking 23748576 0.03");
        assertTrue(actual && actual1);

    }

}
