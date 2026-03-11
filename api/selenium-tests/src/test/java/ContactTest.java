import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactTest {

    private void setValueWithJs(WebDriver driver, WebElement element, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element);
    }

    @Test
    public void testContactForm() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().setSize(new Dimension(430, 932));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            driver.get("file:///C:/Users/moham/gestion-cda/batiment-bloc1-spring/api/frontend/index.html");

            wait.until(d -> js.executeScript("return document.readyState").equals("complete"));

            // Activer explicitement l'onglet Contact
            WebElement contactTab = driver.findElement(
                    By.xpath(
                            "//button[contains(normalize-space(.),'Contact')] | //a[contains(normalize-space(.),'Contact')]"));
            js.executeScript("arguments[0].click();", contactTab);

            Thread.sleep(1000);

            WebElement nom = driver.findElement(By.id("nom"));
            WebElement tel = driver.findElement(By.id("tel"));
            WebElement email = driver.findElement(By.id("email"));
            WebElement message = driver.findElement(By.id("message"));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", nom);
            Thread.sleep(500);

            // Remplissage via JavaScript pour éviter le problème "not interactable"
            setValueWithJs(driver, nom, "Test Selenium");
            setValueWithJs(driver, tel, "0600000000");
            setValueWithJs(driver, email, "test@test.com");
            setValueWithJs(driver, message, "Test automatique");

            WebElement bouton = driver.findElement(By.cssSelector("button[type='submit']"));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", bouton);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", bouton);

            Thread.sleep(3000);

        } finally {
            driver.quit();
        }
    }
}