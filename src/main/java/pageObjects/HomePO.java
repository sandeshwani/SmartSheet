package pageObjects;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePO extends TestBase {

    @FindBy(xpath = "//span[text()='Create']")
    public WebElement create;

    @FindBy(css = "table>tbody>tr")
    public List<WebElement> sheetTypes;

    @FindBy(xpath = "//input[@type='text' and @tabindex='1']")
    public WebElement addName;

    @FindBy(xpath = "//span[text()='OK']")
    public WebElement selectOk;

    @FindBy(xpath = "//span[text()='Sheets']")
    public WebElement heading;

    @FindBy(xpath = "//div[@class='clsHomeDetailHeaderButtons']//div[@class='clsCheckBox clsCheckRadioNoText']")
    public WebElement selectAll;

    @FindBy(css = ".clsBorderBox[data-Client-id='511']")
    public WebElement delete;

    @FindBy(css = ".clsBorderBox[tabindex='2']")
    public WebElement deleteConfirm;

    // Initializing the Page Objects:
    public HomePO() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Create New Sheet with specified type and name
     * @param type
     * @param name
     * @throws Exception
     */
    public void createSheet(String type, String name) throws Exception{
        String selectType;
        waitFor(create,TestBase.HALF_MIN_TIMEOUT);
        create.click();
        for (int i = 0; i< sheetTypes.size(); i++){
            selectType = sheetTypes.get(i).getText();
            if (selectType.equalsIgnoreCase(type)){
                sheetTypes.get(i).click();
                break;
            }
        }
        //addName.click();
        addName.sendKeys(name);
        selectOk.click();
        waitFor(heading,TestBase.HALF_MIN_TIMEOUT);
    }

    /**
     * Invoke Existing sheet using sheet name
     * @param sheetName
     * @throws Exception
     */
    public void invokeSheet(String sheetName) throws Exception {
        waitFor(heading,TestBase.HALF_MIN_TIMEOUT);
        WebElement sheet = driver.findElement(By.xpath("//div[text()='"+sheetName+"']"));
        sheet.click();
    }

    /**
     * Delete all the sheets from the home page
     * @throws Exception
     */
    public void deleteAllSheets() throws Exception{
        selectAll.click();
        delete.click();
        deleteConfirm.click();
    }
}


