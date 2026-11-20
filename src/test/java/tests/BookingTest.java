
package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.BookingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookingTest extends BaseTest {

    private BookingPage bookingPage;

    @BeforeMethod
    public void loginAndOpenBookingPage() {

        String email = System.getenv("SEVASETU_TEST_EMAIL");
        String password = System.getenv("SEVASETU_TEST_PASSWORD");

        Assert.assertNotNull(email, "Test email environment variable missing");
        Assert.assertNotNull(password, "Test password environment variable missing");

        driver.get("https://cgpfrontend.vercel.app/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            wait.until(ExpectedConditions.not(
                    ExpectedConditions.urlContains("/login")
            ));

            System.out.println("Login successful!");
            System.out.println("Current URL: " + driver.getCurrentUrl());

        } catch (org.openqa.selenium.TimeoutException e) {

            System.out.println("Login did not complete.");
            System.out.println("Current URL: " + driver.getCurrentUrl());

            try {
                System.out.println(
                        "Login error: " + loginPage.getErrorMessage()
                );
            } catch (Exception ignored) {
                System.out.println("No visible login error message found.");
            }

            throw new AssertionError(
                    "Customer login failed. Booking tests cannot continue."
            );
        }

        driver.get("https://cgpfrontend.vercel.app/booking");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("address")
        ));

        bookingPage = new BookingPage(driver);
    }

    @Test
    public void verifyBookingFormIsVisible() {
        Assert.assertTrue(
                bookingPage.isBookingFormVisible(),
                "Booking form should be visible"
        );
    }

    @Test
    public void verifyDefaultServiceSelection() {
        Assert.assertTrue(
                bookingPage.isServiceSelected("electrician"),
                "Electrician should be selected by default"
        );
    }

    @Test
    public void verifyServiceCanBeChanged() {
        bookingPage.selectService("plumber");

        Assert.assertTrue(
                bookingPage.isServiceSelected("plumber"),
                "Plumber should be selected"
        );
    }

    @Test
    public void verifyRequiredFields() {
        Assert.assertTrue(bookingPage.isAddressRequired());
        Assert.assertTrue(bookingPage.isScheduleRequired());
    }

    @Test
    public void verifyEmergencyOptionToggle() {
        Assert.assertFalse(bookingPage.isEmergencySelected());

        bookingPage.toggleEmergency();

        Assert.assertTrue(bookingPage.isEmergencySelected());
    }
}
