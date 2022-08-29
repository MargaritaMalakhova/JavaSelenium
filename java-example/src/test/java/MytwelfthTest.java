import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class MytwelfthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mytwelfthTest() {
        List<WebElement> lastestProducts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            driver.navigate().to("http://localhost/litecart/");
            wait.until(titleIs("Online Store | My Store"));
            lastestProducts = driver.findElements(By.cssSelector("#box-latest-products > div > ul > li > a.link"));
            lastestProducts.get(i).click();
            WebElement amountItemsOld = driver.findElement(By.cssSelector("#cart > a.content > span.quantity"));
            WebElement buttonAdd = driver.findElement(By.name("add_cart_product"));
            wait.until(visibilityOf(buttonAdd));
            if(driver.findElement(By.tagName("h1")).getText().compareTo("Yellow Duck") == 0){
                Select size = new Select(driver.findElement(By.name("options[Size]")));
                size.selectByVisibleText("Small");

            }
            buttonAdd.click();
            String spanText = Integer.toString(i + 1);
            wait.until(attributeContains(amountItemsOld, "textContent", spanText));
        }

        driver.navigate().to("http://localhost/litecart/");
        wait.until(titleIs("Online Store | My Store"));
        driver.findElement(By.cssSelector("#cart a.link")).click();
        wait = new WebDriverWait(driver, 10);
        WebElement table = driver.findElement(By.className("dataTable"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println(rows.size());

        for (int i = 0; i < rows.size() - 6; i++) {
            driver.findElement(By.cssSelector("#box-checkout-cart li:nth-child(1) > a")).click();
            WebElement buttonRemove = driver.findElement(By.name("remove_cart_item"));
            WebElement removableElementInTable = driver.findElement(By.cssSelector("table > tbody > tr:nth-child(2) > td.item"));
            buttonRemove.click();
            wait.until(ExpectedConditions.stalenessOf(removableElementInTable));
        }
        WebElement buttonRemove = driver.findElement(By.name("remove_cart_item"));
        WebElement removableElementInTable = driver.findElement(By.cssSelector("table > tbody > tr:nth-child(2) > td.item"));
        buttonRemove.click();
        wait.until(ExpectedConditions.stalenessOf(removableElementInTable));

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
