import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebTests {

    private final static String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testCheckHeader() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();

        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "99 Bottles of Beer");

        driver.close();
    }

    @Test
    public void testCheckMenuSubmitNewLanguage() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();

        Assert.assertEquals(driver.findElement(By.xpath("//li/a[contains(text(), 'Submit new Language')]")).getText().toLowerCase(), "submit new language");

        driver.close();
    }

    @Test
    public void testCheckMenuSubMenuSubmitNewLanguage() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//li/a[contains(text(), 'Submit new Language')]")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(), 'Submit New Language')]")).getText().toLowerCase(), "submit new language");

        driver.close();
    }

    @Test
    public void testCheckSubMenu() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.99-bottles-of-beer.net/abc.html");
        driver.manage().window().maximize();

        Assert.assertEquals(driver.findElement(By.xpath("//li/a[contains(text(), '0-9')]")).getText(), "0-9");

        driver.close();
    }

    @Test
    public void testCheckNames() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[contains(text(), 'Team')]")).click();
        List<WebElement> names = driver.findElements(By.xpath("//h3"));

        String[] expectedNames = {"Oliver Schade", "Gregor Scheithauer", "Stefan Scheler"};
        for (int i = 0; i < names.size(); i++) {
            Assert.assertEquals(names.get(i).getText(), expectedNames[i]);
        }

        driver.close();
    }

    @Test
    public void testCheckCountMenuButtons() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        driver.manage().window().maximize();

        List<WebElement> menu = driver.findElements(By.xpath("//ul[@id = 'menu']/li"));

        Assert.assertEquals(menu.size(), 6);

        driver.close();
    }

    @Test
    public void testCheckRequiredFields() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.99-bottles-of-beer.net/submitnewlanguage.html");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//input[@name = 'submitlanguage']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id = 'main']/p")).getText(), "Error: Precondition failed - Incomplete Input.");

        driver.close();
    }

    @Test
    public void testCheckErrorMessage() {
        System.setProperty("webdriver.chrome.driver", "C:/QA/4_stream/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.99-bottles-of-beer.net/submitnewlanguage.html");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//input[@name = 'submitlanguage']")).click();
        String errorMessage = driver.findElement(By.xpath("//div[@id = 'main']/p")).getText();
        String errorMessage1 = errorMessage.replaceAll("[^ a-zA-z]", "");
        String errorMessage2 = errorMessage.replaceAll("[ \\w]", "");

        Assert.assertTrue(errorMessage1.contains("Error"));
        Assert.assertTrue(errorMessage1.contains("Precondition"));
        Assert.assertTrue(errorMessage1.contains("failed"));
        Assert.assertTrue(errorMessage1.contains("Incomplete"));
        Assert.assertTrue(errorMessage1.contains("Input"));
        Assert.assertTrue(errorMessage2.contains(":-."));

        driver.close();
    }
}
