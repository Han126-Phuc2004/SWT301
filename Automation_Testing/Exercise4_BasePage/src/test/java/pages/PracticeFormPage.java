package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeFormPage extends BasePage {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("userEmail");
    private final By genderMale = By.xpath("//label[text()='Male']");
    private final By mobileInput = By.id("userNumber");
    private final By dobInput = By.id("dateOfBirthInput");
    private final By subjectInput = By.id("subjectsInput");
    private final By hobbySports = By.xpath("//label[text()='Sports']");
    private final By uploadPicture = By.id("uploadPicture");
    private final By addressInput = By.id("currentAddress");
    private final By stateInput = By.id("react-select-3-input");
    private final By cityInput = By.id("react-select-4-input");
    private final By submitBtn = By.id("submit");
    private final By modalTitle = By.id("example-modal-sizes-title-lg");

    public void openFormPage() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(String firstName, String lastName, String email,
                        String gender, String mobile, String birthDate,
                        String subject, String hobbies, String address,
                        String state, String city) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);

        // Select gender
        By genderLocator = By.xpath("//label[text()='" + gender + "']");
        click(genderLocator);

        type(mobileInput, mobile);

        // Date of birth
        click(dobInput);
        driver.findElement(dobInput).sendKeys(Keys.CONTROL + "a");
        driver.findElement(dobInput).sendKeys(birthDate);
        driver.findElement(dobInput).sendKeys(Keys.ENTER);

        // Subject
        type(subjectInput, subject);
        driver.findElement(subjectInput).sendKeys(Keys.ENTER);

        // Select hobbies
        By hobbiesLocator = By.xpath("//label[text()='" + hobbies + "']");
        click(hobbiesLocator);

        // Upload picture - using relative path from project root
        String projectPath = System.getProperty("user.dir");
        String imagePath = projectPath + "/src/test/resources/test-pic.png";
        driver.findElement(uploadPicture).sendKeys(imagePath);

        type(addressInput, address);

        // Select state and city
        type(stateInput, state);
        driver.findElement(stateInput).sendKeys(Keys.ENTER);
        type(cityInput, city);
        driver.findElement(cityInput).sendKeys(Keys.ENTER);

        click(submitBtn);
    }

    public String getModalTitle() {
        try {
            return getText(modalTitle);
        } catch (Exception e) {
            return "Form submission failed";
        }
    }

    public void waitForFormVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
    }

    public void closeAdIfPresent() {
        By closeAdBtn = By.id("close-fixedban");
        try {
            if (isElementVisible(closeAdBtn)) {
                click(closeAdBtn);
            }
        } catch (Exception ignored) {}
    }

    public void closeFooterIfPresent() {
        By footer = By.id("fixedban");
        try {
            if (isElementVisible(footer)) {
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].remove();", driver.findElement(footer));
            }
        } catch (Exception ignored) {}
    }

    public void waitUntilFormIsFullyReady() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
    }

    public void scrollToForm() {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(firstNameInput));
    }
}
