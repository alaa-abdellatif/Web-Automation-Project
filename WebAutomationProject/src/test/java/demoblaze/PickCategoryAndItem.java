package demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class PickCategoryAndItem {
    WebDriver driver = null;
    @BeforeTest
    public void OpenChrome() throws InterruptedException
    {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(chromePath);
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    @Test
    public void Login() throws InterruptedException {
        driver.navigate().to("https://www.demoblaze.com/index.html");
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys("Alaa Maghrabi");
        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[onclick=\"logIn()\"]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.demoblaze.com/index.html");
    }
    @Test
    public void PickCategoryAndItem() throws InterruptedException
    {
        //Choose a category then an item
        driver.findElement(By.linkText("Phones")).click();
        driver.findElement(By.linkText("Nexus 6")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.demoblaze.com/prod.html?idp_=3");

        //Press Add To Cart
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(4000);
        String expectedResult = "Product added.";
        String actualResult = driver.switchTo().alert().getText();
        Assert.assertEquals(actualResult,expectedResult);
        driver.switchTo().alert().accept();
    }
    @AfterTest
    public void CloseDriver () throws InterruptedException
    {
        Thread.sleep(3000);
        driver.quit();
    }
}
