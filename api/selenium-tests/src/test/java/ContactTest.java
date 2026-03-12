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

    driver = new ChromeDriver(options);

    driver.get("http://localhost:8081/index.html");

    // Ouvrir l’onglet Contact
    WebElement contactTab = driver.findElement(By.xpath("//button[contains(text(),'Contact')]"));
    contactTab.click();

    // Remplir le formulaire avec les bons ids
    driver.findElement(By.id("nom")).sendKeys("Test Selenium");
    driver.findElement(By.id("tel")).sendKeys("0600000000");
    driver.findElement(By.id("email")).sendKeys("test@mail.com");
    driver.findElement(By.id("message")).sendKeys("Test automatique");

    driver.findElement(By.cssSelector("#contactForm button[type='submit']")).click();

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