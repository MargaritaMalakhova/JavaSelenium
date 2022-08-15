import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyFifthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFifthTest() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(titleIs("Countries | My Store"));

        WebElement table = driver.findElement(By.className("dataTable"));

        List<WebElement> rows = table.findElements(By.cssSelector("tr.row"));
        int numberRowWithCountryName = 4;
        int numberRowWithZones = 5;
        boolean isSorted = true;
        List<String> countysName = new ArrayList<>();
        List<Integer> indexZone = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {

            countysName.add(rows.get(i).findElements(By.tagName("td")).get(numberRowWithCountryName).getText());
            String amountZones = rows.get(i).findElements(By.tagName("td")).get(numberRowWithZones).getText();
            if (amountZones.compareTo("0") != 0) {
                indexZone.add(i);
            }
        }
        for (int i = 0; i < countysName.size() - 1; i++) {
            if (countysName.get(i).compareTo(countysName.get(i + 1)) >= 0) {
                isSorted = false;
                Assertions.fail("no sorting here");
            }
        }


        for (int i = 0; i < indexZone.size(); i++) {
            driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
            table = driver.findElement(By.className("dataTable"));
            rows = table.findElements(By.cssSelector("tr.row"));
            rows.get(indexZone.get(i)).findElements(By.cssSelector("td")).get(numberRowWithCountryName).findElement(By.tagName("a")).click();
            wait.until(titleIs("Edit Country | My Store"));
            WebElement tableZones = driver.findElement(By.className("dataTable"));
            List<WebElement> rowsZones = tableZones.findElements(By.cssSelector("tr"));

                for (int j = 1; j < rowsZones.size() - 2; j++) {
                    String currentZone = rowsZones.get(j).findElements(By.cssSelector("td")).get(2).getText();
                    String nextZone = rowsZones.get(j+1).findElements(By.cssSelector("td")).get(2).getText();
                    if(currentZone.compareTo(nextZone) >= 0) {
                        isSorted = false;
                        Assertions.fail("no sorting here");
                    }
                }
        }
        }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
