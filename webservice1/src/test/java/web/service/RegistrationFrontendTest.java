package web.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class RegistrationFrontendTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "/Users/mac/Downloads/chromedriver-mac-x64-2/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run headless for automated testing
        driver = new ChromeDriver(options);
    }

    @Test
    public void testRegistrationFormSubmission() {
        // Open the registration form
        driver.get("http://127.0.0.1:8082/register.html");
     // Wait for the fname field to be visible
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement fnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
        
        // Fill in the form
        WebElement firstNameField = driver.findElement(By.id("fname"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("lname"));
        lastNameField.sendKeys("Doe");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("johndoe@example.com");

        WebElement dobField = driver.findElement(By.id("dob"));
        dobField.sendKeys("1990-01-01");

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        // Verify the response
        String responseStatus = driver.findElement(By.tagName("body")).getText().trim();
        assertEquals("{ \"status\": \"ok\" }", responseStatus); // Assuming successful registration
    }

    @After
    public void tearDown() {
        // Close the WebDriver instance
        if (driver != null) {
            driver.quit();
        }
    }
}


