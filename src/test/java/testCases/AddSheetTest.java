package testCases;

import pageObjects.HomePO;
import pageObjects.LoginPO;
import pageObjects.SheetPO;
import base.TestBase;
import utility.JSONProvider;
import org.json.simple.JSONObject;
import org.testng.annotations.*;

public class AddSheetTest extends TestBase {

    LoginPO login;
    HomePO homePage;
    SheetPO sheetPage;

    public AddSheetTest() {
        super();
    }

    /***
     * Setup driver, creates objects from PO's and sets JSON data file
     * @throws Exception
     */
    @BeforeClass(alwaysRun = true)
    public void classSetup() throws Exception {
        setDriver();
        getData(System.getProperty("user.dir") + "/src/test/java/dataFiles/AddSheet.json");
        login = new LoginPO();
        homePage = new HomePO();
        sheetPage = new SheetPO();
        login.login(userName,password);
    }

    /***
     * Method Setup
     */
    @BeforeMethod(alwaysRun = true)
    public void methodSetup() throws Exception {
    }

    /**
     * This test case creates new sheet, invokes that sheet, adds data to
     * specified Row and Column of that sheet and Validates added data.
     * Data like sheet name, sheet type and row/column number are stored in JSON File
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(dataProvider = "getJSON_Data", dataProviderClass = JSONProvider.class)
    public void tc001_addDataToSheet(String rowID, String description, JSONObject testData) throws Exception {
        String sheetType = testData.get("type").toString();
        String sheetName = testData.get("sheetName").toString();
        String fieldData = testData.get("fieldData").toString();
        int row = Integer.parseInt(testData.get("tableRow").toString());
        int col = Integer.parseInt(testData.get("tableColumn").toString());

        homePage.createSheet(sheetType, sheetName);
        homePage.invokeSheet(sheetName);
        sheetPage.updateSheet(row, col, fieldData);
        sheetPage.validateData(row, col, fieldData);
    }

    /***
     * This test case removes data from the sheet created in test case 1 and validates
     * removed data.
     * This testcase depends on test case 1
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(dataProvider = "getJSON_Data", dataProviderClass = JSONProvider.class, dependsOnMethods = "tc001_addDataToSheet")
    public void tc002_removeDataFromSheet(String rowID, String description, JSONObject testData) throws Exception {
        String fieldData = testData.get("fieldData").toString();
        int row = Integer.parseInt(testData.get("tableRow").toString());
        int col = Integer.parseInt(testData.get("tableColumn").toString());

        sheetPage.removeDataFromSheet(row, col);
        sheetPage.validateData(row, col, fieldData);
    }

    /**
     * Method TearDown
     *
     * @throws Exception
     */
    @AfterMethod(alwaysRun = true)
    public void methodTeardown() throws Exception {

    }

    /**
     * Deletes all the sheets created and quits the driver.
     *
     * @throws Exception
     */
    @AfterClass(alwaysRun = true)
    public void classTeardown() throws Exception {
        driver.navigate().back();
        homePage.deleteAllSheets();
        driver.quit();
    }
}
