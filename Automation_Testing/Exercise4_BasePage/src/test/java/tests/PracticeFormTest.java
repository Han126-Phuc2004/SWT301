package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.PracticeFormPage;

class PracticeFormTest extends BaseTest {

    @ParameterizedTest(name = "Valid submission: {0} {1}")
    @CsvFileSource(resources = "/practice-form-data.csv", numLinesToSkip = 1)
    @DisplayName("Practice Form - Parameterized Test")
    void testFormSubmission(String firstName, String lastName, String email,
                            String gender, String mobile, String birthDate,
                            String subject, String hobbies, String address,
                            String state, String city, String expectedMessage) {

        PracticeFormPage formPage = new PracticeFormPage(driver);

        formPage.openFormPage();
        formPage.waitUntilFormIsFullyReady();
        formPage.closeAdIfPresent();
        formPage.closeFooterIfPresent();

        formPage.fillForm(firstName, lastName, email, gender, mobile, birthDate,
                subject, hobbies, address, state, city);

        String actualMessage = formPage.getModalTitle();

        // Kiểm tra thông điệp xuất hiện trong modal title
        Assertions.assertEquals(expectedMessage, actualMessage,
                "❌ Modal title doesn't match expected result");
    }
}
