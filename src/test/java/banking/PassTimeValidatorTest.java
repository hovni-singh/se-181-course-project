package banking;

import org.junit.jupiter.api.BeforeEach;

public class PassTimeValidatorTest {
    Bank bank;
    PassTimeValidator passTimeValidator;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        passTimeValidator = new PassTimeValidator(bank);
        bank.addCheckingAccount("Checking", 13748594, 0.02);
        bank.addSavingsAccount("Savings", 23746537, 0.02);
        bank.addCDAccount("CD", 27382367, 0.04, 300);
    }

}
