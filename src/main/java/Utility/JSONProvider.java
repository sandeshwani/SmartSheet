package Utility;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JSONProvider {

    public static String jsonFile = "";
    public static String testName;

    @DataProvider(name = "getJSON_Data")
    public static Object[][] getData(Method method) throws Exception {
        Object rowId, description;
        Object[][] result;
        testName = method.getName();
        JSONArray testData = (JSONArray) extractJSONData(jsonFile).get(testName);
        List<JSONObject> testDataList = new ArrayList<>();

        for (int i = 0; i < testData.size(); i++) {
            testDataList.add((JSONObject) testData.get(i));
        }
        result = new Object[testDataList.size()][testDataList.get(0).size()];
        for (int i = 0; i < testDataList.size(); i++) {
            rowId = testDataList.get(i).get("rowID");
            description = testDataList.get(i).get("description");
            result[i] = new Object[]{rowId,description,testDataList.get(i)};
        }
        return result;
    }

    public static JSONObject extractJSONData(String jsonFile) throws Exception{
        FileReader reader = new FileReader(jsonFile);
        JSONParser jsonParser = new JSONParser();

        return (JSONObject) jsonParser.parse(reader);
    }

}
