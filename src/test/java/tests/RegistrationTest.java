package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest {

    private static final String REGISTER_URL =
            "https://cgpfrontend.vercel.app/register";

    @Test
    public void verifyRegistrationPageElements() {

        driver.get(REGISTER_URL);

        Assert.assertTrue(driver.findElement(By.name("name")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("email")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("phone")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.name("role")).isDisplayed());
        Assert.assertTrue(driver.findElement(
                By.cssSelector("button[type='submit']")
        ).isDisplayed());

        System.out.println("Registration page elements verified!");
    }

    @Test
    public void verifyCustomerIsSelectedByDefault() {

        driver.get(REGISTER_URL);

        Select role = new Select(driver.findElement(By.name("role")));

        String selectedRole = role.getFirstSelectedOption().getAttribute("value");

        Assert.assertEquals(selectedRole, "customer");

        System.out.println("Customer is selected by default!");
    }

    @Test
    public void verifyCooperativeIdHiddenForCustomer() {

        driver.get(REGISTER_URL);

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        Assert.assertFalse(
                registrationPage.isCooperativeIdVisible(),
                "Cooperative ID should be hidden for Customer"
        );

        System.out.println("Cooperative ID is correctly hidden for Customer!");
    }

    @Test
    public void verifyCooperativeIdVisibleForWorker() {

        driver.get(REGISTER_URL);

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.selectRole("worker");

        Assert.assertTrue(
                registrationPage.isCooperativeIdVisible(),
                "Cooperative ID should appear for Worker"
        );

        System.out.println("Cooperative ID is visible for Worker!");
    }

    @Test
    public void verifyCooperativeIdIsRequiredForWorker() {

        driver.get(REGISTER_URL);

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.selectRole("worker");

        Boolean isRequired = (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "return document.querySelector(" +
                                " 'input[name=\"cooperativeId\"]' ).required;"
                );

        Assert.assertTrue(
                isRequired,
                "Cooperative ID should be required for Worker"
        );

        System.out.println("Cooperative ID required validation verified!");
    }

}

