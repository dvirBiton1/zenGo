import org.dataloader.Try;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
//import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class testZenGo {
    @Test
    void TestSelenium() throws InterruptedException {
        System.setProperty("web-driver.chrome.driver", "chrome.exe"); // Setting system properties of FirefoxDriver
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        System.setProperty("web-driver.chrome.driver", "chrome.exe"); // Setting system properties of FirefoxDriver
        WebDriver driver = new ChromeDriver(options); //Creating an object of chrome
        try {
//        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            driver.get("https://zengo.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//        actions = new Actions(driver);
            boolean successPage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            assertTrue(successPage);
        }
        catch (Exception exception){
            System.out.println("Problem to load the site");
        }
        try {
            WebElement Assets = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div[2]/nav/ul/li[2]/a"));
            Actions action = new Actions(driver);
            action.moveToElement(Assets).build().perform();
            WebElement Ethereum = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[2]"));
            Ethereum.click();
        }
        catch (Exception e){
            System.out.println("The element did not exist");
        }
        try {
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            assertEquals("https://zengo.com/assets/ethereum-wallet/",currentUrl);
        } catch (Exception e) {
            System.out.println("Error to get the url");
        }
        try {
            Thread.sleep(2000);
            WebElement zenGoLogo = driver.findElement
                    (By.xpath("//*[@id=\"page\"]/div[1]/header/div[1]/p[1]/a/img"));
            // Javascript executor to check image
            Boolean zenGoLogoB = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", zenGoLogo);
            //verify if status is true
            assertTrue(zenGoLogoB);
        } catch (Exception e) {
            System.out.println("Error with verifyLogo");
        }
        try {
            Thread.sleep(2000);
            driver.navigate().to("https://zengo.com/");
        } catch (Exception e) {
            System.out.println("Error with navigate to home page");
        }
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Error quit the browser");
        }


    }
}
