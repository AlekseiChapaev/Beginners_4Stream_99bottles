import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTests {

    private final static String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testCheckHeader(){
       System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
       WebDriver driver = new ChromeDriver();

       driver.get(URL);
       driver.manage().window().maximize();

       Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "99 Bottles of Beer");

       driver.close();
    }

    @Test
    public void testCheckMenuSubmitNewLanguage(){
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();

        Assert.assertEquals(driver.findElement(By.xpath("//li/a[contains(text(), 'Submit new Language')]")).getText().toLowerCase(), "submit new language");

        driver.close();
    }

}
