package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

public class LoginServiceTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/mac/Downloads/chromedriver-mac-x64-2/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginSuccess() throws InterruptedException, IOException {
        driver.get("file:///Users/mac/Downloads/8.1P-resources/pages/login.html");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys("ahsan");

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys("ahsan_pass");

        WebElement dobElement = driver.findElement(By.id("dob"));
        dobElement.sendKeys("01-01-1990");

        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();

        // Wait for the title to change after form submission
        wait.until(ExpectedConditions.urlContains("http://127.0.0.1:8082/login?"));
        takeScreenshot("login_success");


        // Get the new title after form submission
        String newTitle = driver.getTitle();
        System.out.println("New Page title: " + newTitle);

        // Assert the login status based on the title
        if (newTitle.contains("success")) {
            System.out.println("Login successful!");
            Assert.assertEquals("success", newTitle);
        } else if (newTitle.contains("fail")) {
            System.out.println("Login failed: Invalid credentials or date of birth");
            Assert.fail("Login failed: Invalid credentials or date of birth");
        } else {
            System.out.println("Unexpected response: " + newTitle);
            Assert.fail("Unexpected response: " + newTitle);
        }
    }

    @Test
    public void testLoginFailureInvalidCredentials() throws IOException {
        driver.get("file:///Users/mac/Downloads/8.1P-resources/pages/login.html");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("invaliduser");

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys("invalidpass");
        takeScreenshot("login_failure_invalid_credentials");
        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("http://127.0.0.1:8082/login?"));
        // Perform checks for login failure scenario
        String title = driver.getTitle();
        Assert.assertEquals("fail", title);
    }

    @Test
    public void testLoginEmptyFields() throws IOException {
        driver.get("file:///Users/mac/Downloads/8.1P-resources/pages/login.html");

        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("http://127.0.0.1:8082/login?"));
     // Capture screenshot on empty fields scenario
        takeScreenshot("login_empty_fields");
        // Perform checks for empty fields scenario
        String title = driver.getTitle();
        Assert.assertEquals("fail", title);
    }

    @Test
    public void testLoginSpecialCharacters() throws IOException {
        driver.get("file:///Users/mac/Downloads/8.1P-resources/pages/login.html");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("user@special");

        WebElement passwordField = driver.findElement(By.id("passwd"));
        passwordField.sendKeys("!@#$%^&*");

        WebElement dobField = driver.findElement(By.id("dob"));
        dobField.sendKeys("01-01-2000");
        // Capture screenshot on special characters scenario
        takeScreenshot("login_special_characters");
        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("http://127.0.0.1:8082/login?"));

        // Perform checks for special characters scenario
        String title = driver.getTitle();
        Assert.assertEquals("fail", title);
    }
    private void takeScreenshot(String filename) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String directory = "./screenshots/";
        File directoryFile = new File(directory);
        
        // Create directory if it doesn't exist
        if (!directoryFile.exists()) {
            directoryFile.mkdirs(); // Create directory including any necessary but nonexistent parent directories
        }
        
        File targetFile = new File(directory + filename + ".png");
        Files.copy(screenshot.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}
