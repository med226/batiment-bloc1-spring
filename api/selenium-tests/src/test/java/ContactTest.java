import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

    driver.get("http://localhost:8080/contact");

    WebElement nom = driver.findElement(By.name("nom"));
    WebElement email = driver.findElement(By.name("email"));
    WebElement message = driver.findElement(By.name("message"));
    WebElement bouton = driver.findElement(By.cssSelector("button[type='submit']"));

    nom.sendKeys("Test Selenium");
    email.sendKeys("test@mail.com");
    message.sendKeys("Message automatique Selenium");
    bouton.click();

    String pageSource = driver.getPageSource().toLowerCase();
    assertTrue(
        pageSource.contains("merci")
            || pageSource.contains("message envoyé")
            || pageSource.contains("succès")
            || pageSource.contains("success"),
        "Le message de confirmation n'a pas été trouvé.");
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}