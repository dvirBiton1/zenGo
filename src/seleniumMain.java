import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class seleniumMain {
    WebDriver driver;
//    Actions actions;
    WebDriverWait wait;
    //A method that is responsible open launch browser
    public void launchbrowser() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        System.setProperty("web-driver.chrome.driver", "chrome.exe"); // Setting system properties of FirefoxDriver
        try {
            driver = new ChromeDriver(options); //Creating an object of chrome
//        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            driver.get("https://zengo.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//        actions = new Actions(driver);
            boolean successPage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            if (successPage){
                System.out.println("The site is displayed successfully");
            }
            else{
                System.out.println("The site is not displayed successfully");
            }
        }
        catch (Exception exception){
            System.out.println("Problem to load the site");
        }
    }
    //A method that is responsible open Assets and then Ethereum
    public void searchEthereum() throws InterruptedException {
        try {
            Thread.sleep(1000);
            WebElement Assets = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div[2]/nav/ul/li[2]/a"));
            Actions action = new Actions(driver);
            action.moveToElement(Assets).build().perform();
            Thread.sleep(1000);
            WebElement Ethereum = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[2]"));
            Ethereum.click();
        }
        catch (Exception e){
            System.out.println("The element did not exist");
        }
    }
    //A method that is responsible verify the url
    public void verifyUrl() throws InterruptedException {
        try{
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        if (currentUrl.equals("https://zengo.com/assets/ethereum-wallet/")){
            System.out.println("You are in the url you want to be");
        }
        else {
            System.out.println("You are not in the url you want to be you are in " + currentUrl);
        }
        }
        catch (Exception e){
            System.out.println("Error to get the url");
        }

    }
    //A method that is responsible verify zenGo Logo
    public void verifyLogo() throws InterruptedException {
        try {
            Thread.sleep(2000);
            WebElement zenGoLogo = driver.findElement
                    (By.xpath("//*[@id=\"page\"]/div[1]/header/div[1]/p[1]/a/img"));
            // Javascript executor to check image
            Boolean zenGoLogoB = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", zenGoLogo);
            //verify if status is true
            if (zenGoLogoB) {
                System.out.println("Logo present");
            } else {
                System.out.println("Logo not present");
            }
        }
        catch (Exception e){
            System.out.println("Error with verifyLogo");
        }
    }
    //A method that is responsible navigate to the home page
    public void homePage() throws InterruptedException {
        try {
            Thread.sleep(2000);
            driver.navigate().to("https://zengo.com/");
        }
        catch (Exception e){
            System.out.println("Error with navigate to home page");
        }

    }
    //A method that is responsible for closing the browser
    public void closeBrowser() {
        try {
            driver.quit();
        }
        catch (Exception e){
            System.out.println("Error quit the browser");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        seleniumMain obj = new seleniumMain();
        obj.launchbrowser();
        obj.searchEthereum();
        obj.verifyUrl();
        obj.verifyLogo();
        obj.homePage();
        obj.closeBrowser();
    }
}
