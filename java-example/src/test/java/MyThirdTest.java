import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyThirdTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myThirdTest()
    {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        List<WebElement> firstLevelMenu = driver.findElements(By.cssSelector("li#app-"));
        System.out.println(firstLevelMenu);

        for (int i = 0; i < firstLevelMenu.size(); i++)
        {
            firstLevelMenu = driver.findElements(By.cssSelector("li#app-"));
            firstLevelMenu.get(i).click();
            driver.findElement(By.tagName("h1"));
            List<WebElement> secondLevelMenu = driver.findElements(By.cssSelector("ul.docs li"));
            System.out.println(secondLevelMenu);

            for (int j=0; j < secondLevelMenu.size(); j++)
            {
                //verifyElementsPresent(driver, By.cssSelector("ul.docs li"));

                secondLevelMenu = driver.findElements(By.cssSelector("ul.docs li"));
                secondLevelMenu.get(j).click();
                driver.findElement(By.tagName("h1"));
            }

        }


    }

    /*void verifyElementsPresent(WebDriver driver, By locator) {
        if( driver.findElements(locator).size() <= 0) {
            throw new RuntimeException("no element found");
        }
    }*/

    @AfterEach
    public void stop()
    {
        //driver.quit();
        //driver = null;
    }
}

