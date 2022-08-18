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


public class MySixthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mySixthTest() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(titleIs("Geo Zones | My Store"));

        WebElement tableCountrys = driver.findElement(By.className("dataTable"));
        List<WebElement> rowsCountrys = tableCountrys.findElements(By.cssSelector("tr.row"));

        int numberRowWithCountryName = 2;
        boolean isSorted = true;

        for (int i = 0; i < rowsCountrys.size(); i++) {
            List<String> countrysZones = new ArrayList<>();
            driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            tableCountrys = driver.findElement(By.className("dataTable"));
            rowsCountrys = tableCountrys.findElements(By.cssSelector("tr.row"));
            rowsCountrys.get(i).findElements(By.cssSelector("td")).get(numberRowWithCountryName).findElement(By.tagName("a")).click();
            wait.until(titleIs("Edit Geo Zone | My Store"));

            WebElement tableZones = driver.findElement(By.className("dataTable"));
            List<WebElement> rowsZones = tableZones.findElements(By.tagName("tr"));
            for (int j = 0; j < rowsZones.size(); j++) {
                WebElement row = rowsZones.get(j);
                List<WebElement> elements = row.findElements(By.cssSelector("select[name*='zone_code'] > option[selected='selected']"));
                if (elements.size() > 0) {
                    String country = elements.get(0).getText();
                    countrysZones.add(country);
                }

            }
            for (int z = 0; z < countrysZones.size() - 1; z++) {
                if (countrysZones.get(z).compareTo(countrysZones.get(z + 1)) >= 0) {
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

