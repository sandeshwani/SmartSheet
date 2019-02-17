# SmartSheet

Selenium Page Object Model using TestNG Framework

Please follow the setup instructions for my project as follows: 
1.	Git Setup:
    1.	To clone the repo from terminal :
    2.	Open terminal.
    3.	Go to the local folder that you want to create the project in.
    4.	Run the command : git clone https://github.com/sameerpatil2006/SmartSheet.git
2.	Intellij Setup:
    1.	Pre-requisite: If your intellij does not have “TestNG” plugin, please install it from Marketplace.
    2.	Open Intellij and Click File-> New -> From Existing Sources.
    3.	Select “SmartSheet” repo you created in step 1.4.
    4.  Select gradle from the list
3.	Running Test Using Test Class:
    1.	The test (AddSheetTest) is located under src/test. Please expand the package and run the test. I have covered two scenarios, Adding new data to sheet and removing data from the sheet.
    2.	Right click on the AddSheetTest and Run As TestNg test should run the test.
4. Running Test Using Suite File:
    1.  Test suite file is located under src/test/java/testcases/testNG.xml. I have added extent report listener to the xml file which will generate Extent Report after execution. The report will be stored under SmartSheetTest/test-output/SmartSheetReport, open report using any browser.
    2. Right click on testNG.xml and select run. It will run both the testcases and generate Extent report after execution. 

My approach to this UX Project:
 
1.	src/main/java:
    1.	base: This contains “TestBase” class that all page objects inherit to. This base class reads the config file, initializes webdriver to use “chromedriver” and does generic UI operations (setting global time limit etc.) before the tests start. 
    2.	Config: This contains config.properties file which contains which browser to use, which url to go to and user account details.
    3.	PageObjects: This contains the page object of Login page, Home Page and Sheet Page. The page object holds the identifiers on the page and methods to do the operations. It also has methods for navigation and validations.
    4.	Utility: contains JSONProvider class which is used to set the data json file for Tests.
2.	Src/main/resources:
    1. Chrome driver and Gecko driver are stored here.
3.	Src/test/java/testcases:
    1.	AddSheetTest: Test class that uses dataprovider that stores data to create new sheet, add data to the sheet and delete data from the sheet. tc002_removeDataFromSheet : This test Method is dependend on tc001_addDataToSheet i.e (tc002 which validates removal of data will be executed only if tc001 passes which adds data to the sheet and validates)
    2.	TestNG.xml : If you want to run the test as a suite, you can use testNG.xml that is located here.  
 4. Src/test/java/dataFiles:
    1. AddSheet.json: Json file used to store test data
 5. Project/test-output:
    1. SmartSheetReport.html : This report is generated after execution of test cases. This report is generated with the help of third party tool Extent Report. To open the report use any browser. 
