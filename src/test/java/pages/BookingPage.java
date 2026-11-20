package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class BookingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By addressField = By.id("address");
    private By latitudeField = By.id("lat");
    private By longitudeField = By.id("lng");
    private By scheduleField = By.id("scheduledAt");
    private By emergencyCheckbox = By.name("isEmergency");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By resultMessage = By.cssSelector(".booking-message");

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectService(String service) {
        By serviceOption = By.cssSelector(
                "input[name='serviceType'][value='" + service + "']"
        );

        WebElement radio = driver.findElement(serviceOption);

        if (!radio.isSelected()) {
            radio.findElement(By.xpath("./ancestor::label")).click();
        }
    }

    public boolean isServiceSelected(String service) {
        return driver.findElement(
                By.cssSelector(
                        "input[name='serviceType'][value='" + service + "']"
                )
        ).isSelected();
    }

    public void enterAddress(String address) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }

    public void enterLatitude(String latitude) {
        driver.findElement(latitudeField).clear();
        driver.findElement(latitudeField).sendKeys(latitude);
    }

    public void enterLongitude(String longitude) {
        driver.findElement(longitudeField).clear();
        driver.findElement(longitudeField).sendKeys(longitude);
    }

    public void enterSchedule(String dateTime) {
        driver.findElement(scheduleField).sendKeys(dateTime);
    }

    public void toggleEmergency() {
        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(emergencyCheckbox)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                checkbox
        );
    }

    public boolean isEmergencySelected() {
        return driver.findElement(emergencyCheckbox).isSelected();
    }

    public boolean isAddressRequired() {
        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].required;",
                        driver.findElement(addressField)
                );
    }

    public boolean isScheduleRequired() {
        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].required;",
                        driver.findElement(scheduleField)
                );
    }

    public boolean isBookingFormVisible() {
        return driver.findElement(addressField).isDisplayed()
                && driver.findElement(scheduleField).isDisplayed()
                && driver.findElement(submitButton).isDisplayed();
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getResultMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultMessage)
        ).getText();
    }
}
