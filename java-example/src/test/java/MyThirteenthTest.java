import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyThirteenthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myThirteenthTest() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(titleIs("Countries | My Store"));

        driver.findElement(By.cssSelector("a.button")).click();
        WebElement table = driver.findElement(By.cssSelector("#content > form > table:nth-child(2)"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        List<WebElement> cells;

        for (int i = 0; i < rows.size(); i++) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            cells = rows.get(i).findElements(By.cssSelector("td > a[href^='http']"));
            if (cells.size() > 0) {
                cells.get(0).click();
                String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
                driver.switchTo().window(newWindow);
                driver.close();
                driver.switchTo().window(mainWindow);
            }

        }
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
