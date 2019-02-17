package base;

import org.openqa.selenium.chrome.ChromeOptions;
import utility.JSONProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class TestBase {

    public static WebDriver driver;
    protected static Properties prop;
    public static int PAGE_LOAD_TIMEOUT = 20;
    public static int IMPLICIT_WAIT = 20;
    public static int HALF_MIN_TIMEOUT = 30;
    public static String userName,password;

    public TestBase() {

        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initializes driver and opens specified URL
     */
    public static void setDriver() throws Exception {
        System.out.println("0");
        String browser = prop.getProperty("browser");
        System.out.println("1");
        String platform = prop.getProperty("platform");
        System.out.println("2");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("no-sandbox");
        chromeOptions.addArguments("disable-dev-shm-usage");
        System.out.println("3");
        userName = prop.getProperty("username");
        System.out.println("4");
        password = prop.getProperty("password");
        System.out.println("5");
        if (browser.equals("chrome") && platform.equalsIgnoreCase("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver.exe");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("chrome") && platform.equalsIgnoreCase("linux")) {
            System.out.println("6");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/resources/chromedriver-2");
            System.out.println("7");
            driver = new ChromeDriver(chromeOptions);
            System.out.println("8");
        } else {
            fail("This test only supports Chrome driver so far. Please make sure you have driver=chrome in the config.properties file. ");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        System.out.println("all cookies deleted");
        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * This method used to wait for elements
     * @param element
     * @param seconds
     * @throws Exception
     */
    public static void waitFor(WebElement element, int seconds) throws Exception {
        if (!exists(element, seconds)) {
            throw new Exception("ERROR: The element " + element + " was Not found within " + seconds + " seconds!");
        }
    }

    /**
     * This method checks if specified element exists or not
     * @param element
     * @param wait
     * @return
     */
    public static boolean exists(WebElement element, int wait) {
        WebDriverWait exists = new WebDriverWait(driver, wait);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        return true;
    }

    /**
     * This method sets the JSON file
     * @param jsonFile
     */
    public void getData(String jsonFile) {
        JSONProvider.jsonFile = jsonFile;
    }
}