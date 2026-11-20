
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton =
            By.cssSelector("button[type='submit']");
    private By errorMessage =
            By.cssSelector("form + p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement field = driver.findElement(emailField);
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = driver.findElement(passwordField);
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        );

        return error.getText();
    }
}
