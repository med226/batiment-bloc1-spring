import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ContactTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void testContactForm() {
        driver.get("http://localhost:8081");

        WebElement tabContact = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("btnContact")));
        tabContact.click();

        WebElement nom = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("nom")));

        WebElement tel = driver.findElement(By.id("tel"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement message = driver.findElement(By.id("message"));

        nom.clear();
        nom.sendKeys("Mohamed");

        tel.clear();
        tel.sendKeys("0600000000");

        email.clear();
        email.sendKeys("test@test.com");

        message.clear();
        message.sendKeys("Test Selenium formulaire contact");

        WebElement bouton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        bouton.click();

        WebElement resultat = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("resultat")));

        wait.until(driver -> !resultat.getText().trim().isEmpty());

        String texteResultat = resultat.getText().trim();
        System.out.println("Message affiché : " + texteResultat);

        assertFalse(texteResultat.isEmpty());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}