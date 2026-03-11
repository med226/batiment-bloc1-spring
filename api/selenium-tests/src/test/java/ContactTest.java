import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

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

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=430,932");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().setSize(new Dimension(430, 932));

            File page = new File("../frontend/index.html");
            driver.get(page.toURI().toString());

            Thread.sleep(2000);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement contactTab = driver.findElement(
                    By.xpath(
                            "//button[contains(normalize-space(.),'Contact')] | //a[contains(normalize-space(.),'Contact')]"));
            js.executeScript("arguments[0].click();", contactTab);

            Thread.sleep(1000);

            WebElement nom = driver.findElement(By.id("nom"));
            WebElement tel = driver.findElement(By.id("tel"));
            WebElement email = driver.findElement(By.id("email"));
            WebElement message = driver.findElement(By.id("message"));
            WebElement bouton = driver.findElement(By.cssSelector("button[type='submit']"));

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