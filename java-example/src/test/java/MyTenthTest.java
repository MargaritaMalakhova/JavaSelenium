import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class MyTenthTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myTenthTest() {
        driver.navigate().to("http://localhost/litecart/");

        wait.until(titleIs("Online Store | My Store"));

        String yellowDuckTitle = driver.findElement(By.cssSelector("#box-campaigns div.name")).getAttribute("textContent");
        String yellowDuckRegularPrice = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getAttribute("textContent");
        String yellowDuckRegularPriceColor = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getCssValue("color");
        Dimension yellowDuckRegularPriceSize = driver.findElement(By.cssSelector("#box-campaigns s.regular-price")).getSize();
        String yellowDuckDiscountPrice = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getAttribute("textContent");
        String yellowDuckDiscountPriceColor = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getCssValue("color");
        Dimension yellowDuckDiscountPriceSize = driver.findElement(By.cssSelector("#box-campaigns strong.campaign-price")).getSize();
        System.out.println(yellowDuckRegularPriceSize);
        System.out.println(yellowDuckDiscountPriceSize);
        System.out.println(yellowDuckDiscountPriceColor);

        if (yellowDuckDiscountPriceSize.getHeight() * yellowDuckDiscountPriceSize.getWidth() <
                yellowDuckRegularPriceSize.getHeight() * yellowDuckRegularPriceSize.getWidth()) {
            Assertions.fail("regular price bigger then discount price");
        }

        Pattern regularPricePatt = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m = regularPricePatt.matcher(yellowDuckRegularPriceColor);
        Integer r;
        Integer g;
        Integer b;
        if (m.matches()) {
            r = Integer.valueOf(m.group(1));
            g = Integer.valueOf(m.group(2));
            b = Integer.valueOf(m.group(3));
            if (r != g || g != b) {
                Assertions.fail("color is wrong");
            }
        } else {
            Assertions.fail("wrong rgb-format");
        }

        Pattern discountPricePatt = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m2 = discountPricePatt.matcher(yellowDuckDiscountPriceColor);

        if (m2.matches()) {
            r = Integer.valueOf(m2.group(1));
            g = Integer.valueOf(m2.group(2));
            b = Integer.valueOf(m2.group(3));
            if (g != 0 || b != 0) {
                Assertions.fail("color is wrong");
            }
        } else {
            Assertions.fail("wrong rgb-format");
        }

        driver.findElement(By.cssSelector("#box-campaigns")).findElement(By.tagName("a")).click();
        String yellowDuckTitle2 = driver.findElement(By.cssSelector("h1")).getAttribute("textContent");
        String yellowDuckRegularPrice2 = driver.findElement(By.cssSelector("#box-product s.regular-price")).getAttribute("textContent");
        String yellowDuckRegularPrice2Color = driver.findElement(By.cssSelector("#box-product s.regular-price")).getCssValue("color");
        Dimension yellowDuckRegularPrice2Size = driver.findElement(By.cssSelector("#box-product s.regular-price")).getSize();
        String yellowDuckDiscountPrice2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getAttribute("textContent");
        String yellowDuckDiscountPriceColor2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getCssValue("color");
        Dimension yellowDuckDiscountPriceSize2 = driver.findElement(By.cssSelector("#box-product strong.campaign-price")).getSize();

        if (yellowDuckTitle.compareTo(yellowDuckTitle2) != 0) {
            Assertions.fail("titles of the product aren't equals");
        }

        if (yellowDuckRegularPrice.compareTo(yellowDuckRegularPrice2) != 0) {
            Assertions.fail("regual prices of the product aren't equals");
        }

        if (yellowDuckDiscountPrice.compareTo(yellowDuckDiscountPrice2) != 0) {
            Assertions.fail("discount prices of the product aren't equals");
        }


        if (yellowDuckDiscountPriceSize2.getHeight() * yellowDuckDiscountPriceSize2.getWidth() <
                yellowDuckRegularPrice2Size.getHeight() * yellowDuckRegularPrice2Size.getWidth()) {
            Assertions.fail("regular price bigger then discount price");
        }

        regularPricePatt = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        m = regularPricePatt.matcher(yellowDuckRegularPrice2Color);
        if (m.matches()) {
            r = Integer.valueOf(m.group(1));
            g = Integer.valueOf(m.group(2));
            b = Integer.valueOf(m.group(3));
            if (r != g || g != b) {
                Assertions.fail("color is wrong");
            }
        } else {
            Assertions.fail("wrong rgb-format");
        }

        discountPricePatt = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        m2 = discountPricePatt.matcher(yellowDuckDiscountPriceColor2);

        if (m2.matches()) {
            r = Integer.valueOf(m2.group(1));
            g = Integer.valueOf(m2.group(2));
            b = Integer.valueOf(m2.group(3));
            if (g != 0 || b != 0) {
                Assertions.fail("color is wrong");
            }
        } else {
            Assertions.fail("wrong rgb-format");
        }
    }

    @AfterEach
    public void stop () {
        driver.quit();
        driver = null;
    }
}
