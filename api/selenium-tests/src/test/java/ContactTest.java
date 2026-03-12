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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  void setUp() {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    driver = new ChromeDriver(options);

    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
  }

  @Test
  void testContactForm() {

    driver.get("http://localhost:8080");

    // Aller à l'écran contact
    WebElement tabContact = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(),'Contact')]")));

    tabContact.click();

    // attendre champ nom
    WebElement nom = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("nom")));

    WebElement tel = driver.findElement(By.id("tel"));
    WebElement email = driver.findElement(By.id("email"));
    WebElement message = driver.findElement(By.id("message"));

    nom.sendKeys("Mohamed");
    tel.sendKeys("0600000000");
    email.sendKeys("test@test.com");
    message.sendKeys("Test Selenium formulaire contact");

    WebElement bouton = driver.findElement(By.cssSelector("button[type='submit']"));

    bouton.click();

    WebElement resultat = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("resultat")));

    assertTrue(resultat.getText().length() > 0);
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}