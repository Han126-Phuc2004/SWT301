import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import phuctv.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @ParameterizedTest(name = "Test {index}: username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean result = accountService.registerAccount(username, password, email);
        assertEquals(expected, result,
                String.format("Registration with username=%s, password=%s, email=%s should be %s",
                        username, password, email, expected));
    }

    @ParameterizedTest(name = "Email Validation Test {index}: email={0}, expected={1}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testIsValidEmail(String username, String password, String email, boolean expected) {
        boolean result = accountService.isValidEmail(email);
        boolean isEmailExpected = email != null && !email.isEmpty() && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        assertEquals(isEmailExpected, result,
                String.format("Email validation for %s should be %s", email, isEmailExpected));
    }

    @ParameterizedTest(name = "Password Length Test {index}: password={0}, expected={1}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testPasswordLength(String username, String password, String email, boolean expected) {
        boolean isPasswordValid = password != null && password.length() > 6;
        boolean result = accountService.registerAccount(username, password, email);
        if (!isPasswordValid) {
            assertEquals(false, result, "Password length <= 6 should fail registration");
        }
    }

    @ParameterizedTest(name = "Special Char Username Test {index}: username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testUsernameWithSpecialCharactersFromCSV(String username, String password, String email, boolean expected) {
        if (username != null && !username.matches("^[a-zA-Z0-9]+$")) {
            boolean result = accountService.registerAccount(username, password, email);
            org.junit.jupiter.api.Assertions.assertEquals(expected, result,
                "Username with special characters should NOT be allowed");
        }
    }
}