package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.makemytrip.com/");

        String currentURL = driver.getCurrentUrl();

        if(currentURL.contains("makemytrip")){
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException{
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//label[@for='fromCity']/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("blr");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin')]//li[1]")).click();

        driver.findElement(By.xpath("//label[@for='toCity']/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("del");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin')]//li[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//p[text()='29']")).click();
        driver.findElement(By.xpath("//a[text()='Search']")).click();

        Thread.sleep(10000);

        if (driver.findElement(By.xpath("//div[@class='commonOverlay ']/span")).isDisplayed()){
            driver.findElement(By.xpath("//div[@class='commonOverlay ']/span")).click();
        }

        String price = driver.findElement(By.xpath("(//div[contains(@class,'listingCard')]//div[contains(@class,'textRight')])[1]")).getText();

        price = price.replace("per adult", "").trim();

        System.out.println("Flight Price: "+ price);

        System.out.println("end Test case: testCase02");
    }


    public void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//li[@class='menu_Trains']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//label[@for='fromCity']/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("ypr");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin')]//li[1]")).click();
        Thread.sleep(2000);

        // driver.findElement(By.xpath("//label[@for='toCity']/input")).click();
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("ndls");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin')]//li[1]")).click();
        Thread.sleep(2000);

        // driver.findElement(By.xpath("//p[@data-cy='departureDate']")).click();
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Day'][text()='29']")).click();
        Thread.sleep(2000);

        // driver.findElement(By.xpath("//div[@data-cy='classValue']")).click();
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//ul[@class='travelForPopup']/li[@data-cy='3A']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Search']")).click();
        Thread.sleep(5000);

        String price = driver.findElement(By.xpath("(//div[contains(@class,'single-train-detail')][1]//div[contains(@class,'ticket-price')])[1]")).getText();
        
        System.out.println("Train Price: "+ price);
        
        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException{
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//li[@class='menu_Buses']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//label[@for='fromCity']/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("bangl");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin')]//li[1]")).click();
        Thread.sleep(2000);

        // driver.findElement(By.xpath("//label[@for='toCity']/input")).click();
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("del");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'autoSuggestPlugin')]//li[1]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Day'][text()='29']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='search_button']")).click();

        Thread.sleep(5000);

        String error = driver.findElement(By.xpath("//div[contains(@class,'error-view')]//span[@class='error-title']")).getText();

        if(error.contains("No buses found for 29")){
            System.err.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

        System.out.println("end Test case: testCase04");

    }


}
