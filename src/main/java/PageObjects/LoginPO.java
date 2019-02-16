package PageObjects;

import Utility.CommonUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPO extends CommonUtility {

    @FindBy(linkText = "Log In")
    public WebElement logIn;

    @FindBy(id = "loginEmail")
    public WebElement loginEmail;

    @FindBy(xpath = "//*[@type='submit' and @value='Continue']")
    public WebElement contin;

    @FindBy(id = "loginPassword")
    public WebElement password;

    @FindBy(xpath = "//*[@type='submit' and @value='Log In']")
    public WebElement signIn;

    @FindBy(css = "div.clsAccountMenu > span >.clsAvatar")
    public WebElement accountMenu;

    @FindBy(xpath = "//span[text()='Sign Out']")
    public WebElement signout;


    // Initializing the Page Objects:
    public LoginPO() {
        PageFactory.initElements(driver, this);
    }

    //Fetch user account details from config.properties
    String userName = prop.getProperty("username");
    String pwd = prop.getProperty("password");

    /***
     * This method enter's user details and login to Smartsheet Account
     * @throws Exception
     */
    public void login() throws Exception{
        logIn.click();
        waitFor(loginEmail,CommonUtility.HALF_MIN_TIMEOUT);
        loginEmail.click();
        loginEmail.sendKeys(userName);
        contin.click();
        waitFor(password,CommonUtility.HALF_MIN_TIMEOUT);
        password.sendKeys(pwd);
        signIn.click();
    }

    /**
     * This method logouts user from Smartsheet
     * @throws Exception
     */
    public void signout() throws Exception {
        accountMenu.click();
        signout.click();
    }
}
