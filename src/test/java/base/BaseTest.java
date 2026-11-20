package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        System.out.println("Chrome browser launched successfully!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            System.out.println("Chrome browser closed successfully!");
        }
    }
}
