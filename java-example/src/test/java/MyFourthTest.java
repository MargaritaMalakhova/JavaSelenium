import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyFourthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFourthTest() {
        driver.navigate().to("http://localhost/litecart/");

        wait.until(titleIs("Online Store | My Store"));

        List<WebElement> productsList = driver.findElements(By.cssSelector("li.product"));
        System.out.println(productsList);

        for (int i = 0; i < productsList.size(); i++) {
            productsList = driver.findElements(By.cssSelector("li.product"));
            List<WebElement> sticker = productsList.get(i).findElements(By.className("sticker"));
            if (sticker.size() != 1) {
                Assertions.fail("there should be only one 'sticker' element");
            }

        }
    }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
