package com.swp391.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By email = By.name("email");
    private By password = By.name("password");
    private By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("http://localhost:8080/Login.jsp");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("form")));
    }

    public void login(String user, String pass) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(user);

        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);

        driver.findElement(loginBtn).click();
    }

    public boolean isLoginSuccess() {
        return wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("Dashboard"),
                ExpectedConditions.urlContains("Home"),
                ExpectedConditions.urlContains("JobSeekerProfile"),
                ExpectedConditions.not(ExpectedConditions.urlContains("Login"))
        ));
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
