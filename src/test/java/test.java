import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class test {
    WebDriver driver;
    String baseUrl;
    WebDriverWait wait1;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        driver = new ChromeDriver();
     baseUrl = "https://sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        

    }

    @Test
    public void testInsurance() {
        driver.get(baseUrl + "/");
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        driver.findElement(By.xpath("//header[@class='header']//div[@class='hd-ft-region__title']")).click();


        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[ text()= 'Выбор региона']"))));
       Assert.assertEquals("Выбор региона", driver.findElement(By.xpath("//h4[ text()= 'Выбор региона']")).getText());
        fillField(By.xpath("//*[contains(@class,'kit-input')][contains(@type,'search')]"),"Нижегородская область");
        driver.findElement(By.xpath("//a[text()='Нижегородская область']")).click();
        Assert.assertEquals("Нижегородская область", driver.findElement(By.xpath("//header[@class='header']//div[@class='hd-ft-region__title']")).getText());
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
        driver.findElement(By.xpath("//ul[@class='footer__social']")).isEnabled();
        System.out.println("Тест прошел успешно");
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    private void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);

    }

}






