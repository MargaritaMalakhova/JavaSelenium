import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyFourteenthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFourteenthTest() {
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }
        wait.until(titleIs("Catalog | My Store"));

        WebElement table = driver.findElement(By.className("dataTable"));

        List<WebElement> rows = table.findElements(By.cssSelector("tr.row"));
        System.out.println(rows.size());

        for (int i = 3; i < rows.size(); i++) {
            driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            table = driver.findElement(By.className("dataTable"));
            rows = table.findElements(By.cssSelector("tr.row"));
            rows.get(i).findElement(By.cssSelector("td:nth-child(3) > a")).click();
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                System.out.println(l);
            }
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}



