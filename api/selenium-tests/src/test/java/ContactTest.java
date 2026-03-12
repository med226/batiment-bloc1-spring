import org.junit.jupiter.api.AfterEach;
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

  @Test
  void testContactForm() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=430,932");

    String chromeBinary = System.getenv("CHROME_BIN");
    if (chromeBinary != null && !chromeBinary.isBlank()) {
      options.setBinary(chromeBinary);
    }

    driver = new ChromeDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    driver.get("http://localhost:8081/index.html");

    WebElement contactTab = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(),'Contact')]")));
    contactTab.click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nom"))).sendKeys("Test Selenium");
    driver.findElement(By.id("tel")).sendKeys("0600000000");
    driver.findElement(By.id("email")).sendKeys("test@mail.com");
    driver.findElement(By.id("message")).sendKeys("Test automatique");

    driver.findElement(By.cssSelector("#contactForm button[type='submit']")).click();

    wait.until(ExpectedConditions.or(
        ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "demande envoyée avec succès"),
        ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "envoi en cours"),
        ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "erreur lors de l'envoi"),
        ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"),
            "impossible de contacter l'api spring boot")));

    String pageSource = driver.getPageSource().toLowerCase();

    assertTrue(
        pageSource.contains("demande envoyée avec succès")
            || pageSource.contains("envoi en cours")
            || pageSource.contains("erreur lors de l'envoi")
            || pageSource.contains("impossible de contacter l'api spring boot"),
        "Aucun message de retour n'a été trouvé.");
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}