
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.name("name");
    private By emailField = By.name("email");
    private By phoneField = By.name("phone");
    private By passwordField = By.name("password");
    private By roleDropdown = By.name("role");
    private By cooperativeIdField = By.name("cooperativeId");
    private By registerButton = By.cssSelector("button[type='submit']");
    private By message = By.cssSelector("form + p");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void selectRole(String role) {
        Select select = new Select(driver.findElement(roleDropdown));
        select.selectByValue(role);
    }

    public boolean isCooperativeIdVisible() {
        return !driver.findElements(cooperativeIdField).isEmpty()
                && driver.findElement(cooperativeIdField).isDisplayed();
    }

    public void enterCooperativeId(String cooperativeId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cooperativeIdField))
                .sendKeys(cooperativeId);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(message)
        ).getText();
    }

    public boolean isRegisterButtonEnabled() {
        return driver.findElement(registerButton).isEnabled();
    }
}
