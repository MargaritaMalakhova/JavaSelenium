import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyEighthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myEighthTest() {
        driver.navigate().to("http://localhost/litecart/");

        wait.until(titleIs("Online Store | My Store"));



        long unixTime = System.currentTimeMillis();
        String time = Long.toString(unixTime);
        String email = time + "@mail.ru";
        String password = "+password";
        WebElement table = driver.findElement(By.cssSelector(".content table"));

        table.findElements(By.cssSelector("tr")).get(4).findElement(By.tagName("a")).click();

        driver.findElement(By.name("tax_id")).sendKeys("123456789");
        driver.findElement(By.name("company")).sendKeys("new comp");
        driver.findElement(By.name("firstname")).sendKeys("firstname");
        driver.findElement(By.name("lastname")).sendKeys("lastname");
        driver.findElement(By.name("address1")).sendKeys("address1");
        driver.findElement(By.name("address2")).sendKeys("address2");
        driver.findElement(By.name("postcode")).sendKeys("98745");
        driver.findElement(By.name("city")).sendKeys("city");
        driver.findElement(By.className("select2")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("United States");
        driver.findElement(By.cssSelector("select > option[value='US']")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+11234567891");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name = 'create_account']")).click();
        wait.until(titleIs("Online Store | My Store"));
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name = 'login']")).click();
        driver.findElement(By.linkText("Logout")).click();
        }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}

