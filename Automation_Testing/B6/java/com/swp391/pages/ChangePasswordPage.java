package com.swp391.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ChangePasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openProfilePage() {
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.nav-link[href='/JobSeekerProfile']")));
        profileLink.click();
    }

    public void openChangePasswordForm() {
        WebElement changeLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.btn.btn-outline-primary[href='ChangePassword']")));
        changeLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("form")));
    }

    public void changePassword(String current, String newPass, String confirmPass) {
        WebElement currentPasswordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("currentPassword")));
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys(current);

        WebElement newPasswordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("newPassword")));
        newPasswordInput.clear();
        newPasswordInput.sendKeys(newPass);

        WebElement confirmPasswordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("confirmPassword")));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPass);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Change Password')]")));
        submitButton.click();
    }

    public boolean isSuccessMessageDisplayed(String message) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.cssSelector(".alert-success"), message));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed(String errorMessage) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.cssSelector(".alert-danger, .error-message"), errorMessage));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
