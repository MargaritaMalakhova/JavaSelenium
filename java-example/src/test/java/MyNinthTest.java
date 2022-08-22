import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyNinthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myNinthTest() {
        long unixTime = System.currentTimeMillis();
        String code = Long.toString(unixTime);
        String productName = "TestProduct" + code;

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        wait.until(titleIs("Catalog | My Store"));
        driver.findElement(By.cssSelector("#content div a:nth-child(2)")).click();
        wait.until(titleIs("Add New Product | My Store"));
        driver.findElement(By.cssSelector("div#tab-general table label:nth-child(3)")).click();
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElement(By.name("code")).sendKeys(code);
        driver.findElement(By.cssSelector("input[data-name ='Root']")).click();
        driver.findElement(By.cssSelector("input[data-name ='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("input[data-name ='Subcategory']")).click();
        driver.findElement(By.cssSelector("input[data-name ='Subcategory']")).click();
        driver.findElement(By.cssSelector("input[name ='product_groups[]'][value = '1-1']")).click();
        driver.findElement(By.cssSelector("input[name ='product_groups[]'][value = '1-2']")).click();
        driver.findElement(By.cssSelector("input[name ='product_groups[]'][value = '1-3']")).click();
        driver.findElement(By.name("quantity")).sendKeys("5");

        WebElement addFile = driver.findElement(By.cssSelector("input[type='file']"));
        File file = new File(System.getProperty("user.dir"),"img\\File_For_Attach.jpg");
        String picturePath = file.getAbsolutePath();
        addFile.sendKeys(picturePath);

        driver.findElement(By.cssSelector("input[name ='date_valid_from']")).sendKeys("20.08.2022");
        driver.findElement(By.cssSelector("input[name ='date_valid_to']")).sendKeys("20.08.2023");

        driver.findElement(By.cssSelector("#content li:nth-child(2) > a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByVisibleText("ACME Corp.");
        Select supplier = new Select(driver.findElement(By.name("supplier_id")));
        supplier.selectByVisibleText("-- Select --");
        driver.findElement(By.name("keywords")).sendKeys("TestProduct");
        driver.findElement(By.name("short_description[en]")).sendKeys("Lorem ipsum dolor sit amet");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Lorem ipsum dolor sit amet, ut qui velit inermis menandri.");
        driver.findElement(By.name("head_title[en]")).sendKeys("Head Title For TestProduct");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Meta Description For TestProduct");
        driver.findElement(By.cssSelector("#content li:nth-child(4) > a")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("purchase_price")).sendKeys("5");
        Select purchasePriceCurrencyCode = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        purchasePriceCurrencyCode.selectByVisibleText("US Dollars");
        Select taxClassId = new Select(driver.findElement(By.name("tax_class_id")));
        taxClassId.selectByVisibleText("-- Select --");
        driver.findElement(By.name("prices[USD]")).sendKeys("50");
        driver.findElement(By.name("prices[EUR]")).sendKeys("50");
        driver.findElement(By.name("save")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText(productName));
    }



    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
