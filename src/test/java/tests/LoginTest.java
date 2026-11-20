package tests;

import base.BaseTest;
import pages.LoginPage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private static final String LOGIN_URL =
            "https://cgpfrontend.vercel.app/login";

    @Test(priority = 1)
    public void verifyLoginPageElements() {

        driver.get(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(
                driver.findElement(By.name("email")).isDisplayed()
        );

        Assert.assertTrue(
                driver.findElement(By.name("password")).isDisplayed()
        );

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("button[type='submit']")
                ).isDisplayed()
        );

        System.out.println("Login page elements verified!");
    }

    @Test(priority = 2)
    public void verifyValidCustomerLogin() {

        String email = System.getenv("SEVASETU_TEST_EMAIL");
        String password = System.getenv("SEVASETU_TEST_PASSWORD");
        System.out.println("Email configured: " + (email != null));
        System.out.println("Password configured: " + (password != null));
        System.out.println("Password length: " +
                (password == null ? 0 : password.length()));

        Assert.assertNotNull(email, "Test email environment variable missing");
        Assert.assertNotNull(password, "Test password environment variable missing");

        driver.get(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlToBe(LOGIN_URL)
        ));

        System.out.println("URL after login: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());
    }

    @Test(priority = 3)
    public void verifyInvalidPassword() {

        String email = System.getenv("SEVASETU_TEST_EMAIL");

        Assert.assertNotNull(email, "Test email environment variable missing");

        driver.get(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(email, "DefinitelyWrongPassword123!");

        String message = loginPage.getErrorMessage();

        Assert.assertFalse(
                message.isBlank(),
                "Expected login error message was not displayed"
        );

        System.out.println("Invalid password correctly rejected!");
    }

    @Test(priority = 4)
    public void verifyEmptyFieldsValidation() {

        driver.get(LOGIN_URL);

        WebElement emailField =
                driver.findElement(By.name("email"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Boolean isValid = (Boolean) js.executeScript(
                "return arguments[0].checkValidity();",
                emailField
        );

        Assert.assertFalse(
                isValid,
                "Empty email field should fail required validation"
        );

        System.out.println("Empty field validation verified!");
    }
}
