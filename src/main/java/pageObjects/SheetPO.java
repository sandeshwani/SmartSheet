package pageObjects;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SheetPO extends TestBase {
LoginPO login = new LoginPO();

    public static final String table = "//table[@class='clsGridTable']/tbody";

    @FindBy(className = "clsInputSizingNodeW")
    public WebElement field;

    Actions actions = new Actions(driver);
    public SheetPO() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Insert specified data at specified location
     * @param rowNum
     * @param columnNum
     * @param fieldData
     * @throws Exception
     */
     public void updateSheet(int rowNum, int columnNum, String fieldData) throws Exception{
        waitFor(login.accountMenu,TestBase.HALF_MIN_TIMEOUT);
        WebElement block = driver.findElement(By.xpath(table+"/tr["+rowNum+"]/td["+columnNum+"]"));
        actions.moveToElement(block);
        actions.click();
        actions.sendKeys(fieldData);
        actions.build().perform();
    }

    /**
     * Remove data from specified row and column number
     * @param rowNum
     * @param columnNum
     * @throws Exception
     */
    public void removeDataFromSheet(int rowNum, int columnNum) throws Exception{
        waitFor(login.accountMenu,TestBase.HALF_MIN_TIMEOUT);
        WebElement block = driver.findElement(By.xpath(table+"/tr["+rowNum+"]/td["+columnNum+"]"));
        actions.moveToElement(block);
        actions.doubleClick();
        actions.sendKeys(Keys.DELETE);
        actions.build().perform();
    }

    /**
     * Validate data at specified field of sheet
     * @param rowNum
     * @param columnNum
     * @param actualData
     * @throws Exception
     */
    public void validateData(int rowNum, int columnNum, String actualData) throws Exception{
        waitFor(login.accountMenu,TestBase.HALF_MIN_TIMEOUT);
        WebElement block = (driver.findElement(By.xpath(table+"/tr["+rowNum+"]/td["+columnNum+"]" +
                "/table/tbody/tr/td["+columnNum+"]/div")));
        if (block.getText().equalsIgnoreCase(" ")){
            block = field;
        }
        String valueExp = block.getText().split(" ")[0];
        Assert.assertEquals(actualData,valueExp);
        System.out.print("done");
    }
}
