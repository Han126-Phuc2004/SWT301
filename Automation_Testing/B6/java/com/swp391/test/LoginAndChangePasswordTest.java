package com.swp391.test;

import com.swp391.pages.LoginPage;
import com.swp391.pages.ChangePasswordPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.WebDriver;

public class LoginAndChangePasswordTest {

    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            // 1. Test đăng nhập sai mật khẩu
            testLoginWrongPassword(driver);

            // 2. Đăng nhập đúng để test đổi mật khẩu
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("vansaolol00@gmail.com", "Han126...7"); // Mật khẩu hiện tại

            if (loginPage.isLoginSuccess()) {
                System.out.println("✅ Đăng nhập thành công!");

                // 3. Test xác nhận mật khẩu không khớp
                testChangePasswordConfirmMismatch(driver);

                // 4. Test mật khẩu hiện tại sai
                testChangePasswordWrongCurrent(driver);

                // 5. Test mật khẩu mới không hợp lệ
                testChangePasswordNewTooShort(driver);

                // 6. Đổi mật khẩu thành công
                driver.get("http://localhost:8080/JobSeekerProfile");
                ChangePasswordPage changePage = new ChangePasswordPage(driver);
                changePage.openProfilePage();
                changePage.openChangePasswordForm();
                changePage.changePassword("Han126...7", "Han126...8", "Han126...8");

                if (changePage.isSuccessMessageDisplayed("Password changed successfully!")) {
                    System.out.println("✅ Đổi mật khẩu thành công!");
                } else {
                    System.out.println("❌ Đổi mật khẩu thất bại.");
                }

            } else {
                System.out.println("❌ Đăng nhập thất bại.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed");
            }
        }
    }

    public static void testLoginWrongPassword(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("vansaolol00@gmail.com", "wrongpassword");
        if (loginPage.isErrorMessageDisplayed("Incorrect email or password!")) {
            System.out.println("✅ Test đăng nhập sai mật khẩu: Passed");
        } else {
            System.out.println("❌ Test đăng nhập sai mật khẩu: Failed");
        }
    }

    public static void testChangePasswordConfirmMismatch(WebDriver driver) {
        driver.get("http://localhost:8080/JobSeekerProfile");
        ChangePasswordPage changePage = new ChangePasswordPage(driver);
        changePage.openProfilePage();
        changePage.openChangePasswordForm();
        changePage.changePassword("Han126...7", "Han126...8", "KhongKhop");
        if (changePage.isErrorMessageDisplayed("New password and confirm password do not match")) {
            System.out.println("✅ Test xác nhận mật khẩu sai: Passed");
        } else {
            System.out.println("❌ Test xác nhận mật khẩu sai: Failed");
        }
    }

    public static void testChangePasswordWrongCurrent(WebDriver driver) {
        driver.get("http://localhost:8080/JobSeekerProfile");
        ChangePasswordPage changePage = new ChangePasswordPage(driver);
        changePage.openProfilePage();
        changePage.openChangePasswordForm();
        changePage.changePassword("wrongcurrent", "Han126...8", "Han126...8");
        if (changePage.isErrorMessageDisplayed("Current password is incorrect")) {
            System.out.println("✅ Test mật khẩu hiện tại sai: Passed");
        } else {
            System.out.println("❌ Test mật khẩu hiện tại sai: Failed");
        }
    }

    public static void testChangePasswordNewTooShort(WebDriver driver) {
        driver.get("http://localhost:8080/JobSeekerProfile");
        ChangePasswordPage changePage = new ChangePasswordPage(driver);
        changePage.openProfilePage();
        changePage.openChangePasswordForm();
        changePage.changePassword("Han126...7", "123", "123");
        if (changePage.isErrorMessageDisplayed("Password must be at least 8 characters and include uppercase, lowercase, number, and special character")) {
            System.out.println("✅ Test mật khẩu mới quá ngắn: Passed");
        } else {
            System.out.println("❌ Test mật khẩu mới quá ngắn: Failed");
        }
    }
}
