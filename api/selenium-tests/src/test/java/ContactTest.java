import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=430,932");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().setSize(new Dimension(430, 932));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            File page = new File("../frontend/index.html");
            Assertions.assertTrue(page.exists(), "Le fichier frontend/index.html est introuvable");

            driver.get(page.toURI().toString());

            wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

            WebElement contactTab = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//button[contains(normalize-space(.),'Contact')] | //a[contains(normalize-space(.),'Contact')]")));
            js.executeScript("arguments[0].click();", contactTab);

            WebElement nom = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nom")));
            WebElement tel = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tel")));
            WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
            WebElement bouton = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));

            setValueWithJs(driver, nom, "Test Selenium");
            setValueWithJs(driver, tel, "0600000000");
            setValueWithJs(driver, email, "test@test.com");
            setValueWithJs(driver, message, "Test automatique");

            js.executeScript("arguments[0].click();", bouton);

            Thread.sleep(1000);
        } finally {
            driver.quit();
        }
    }
}