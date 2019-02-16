package Utility;

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

public class CommonUtility {

    public static WebDriver driver;
    protected static Properties prop;
    public static int PAGE_LOAD_TIMEOUT = 20;
    public static int IMPLICIT_WAIT = 20;
    public static int HALF_MIN_TIMEOUT = 30;


    public CommonUtility() {

        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Properties/config.properties");
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
    public static void setDriver() {
        String browser = prop.getProperty("browser");
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            fail("This test only supports Chrome driver so far. Please make sure you have driver=chrome in the config.properties file. ");
        }
        driver.manage().window().maximize();
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