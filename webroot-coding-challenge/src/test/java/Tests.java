import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class Tests {

	JSONArray testData;
	  String ipOne = "1.2.3.4";
	  String ipTwo = "1.2.3.5";
	  ReadData rd;
	  
	  @Before
	  public void getTestData(){
		  
		  rd = new ReadData();
		  // read the json test data file as a string
		  // then convert it to json array
		  try (InputStream is = new FileInputStream("testData.json")) {
		        String file = IOUtils.toString(is, StandardCharsets.UTF_8);
		        testData = new JSONArray(file);
		        
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  }
	  
	  @Test
	  public void verifyObjOne() throws FileNotFoundException, ParseException {
		  
		  JSONObject firstObj = testData.getJSONObject(0);
		  
		  // returnData method returns computed data
		  JSONObject computedData = rd.returnData().getJSONObject("test");
		  int expectedScore = firstObj.getInt("score");
		  
		  int expectedIpOneCount = firstObj.getInt(ipOne);
		  int expectedIpTwoCount = firstObj.getInt(ipTwo);
		  
		  // assert the score is equal to 34
		  assertEquals(computedData.getInt("score"), expectedScore);

		  // assert ipOneCount is 2
		  assertEquals(computedData.getInt(ipOne), expectedIpOneCount);
		  
		  // assert ipTwo count is 1
		  assertEquals(computedData.getInt(ipTwo), expectedIpTwoCount);
	  }
	  
	  @Test
	  public void verifyObjTwo() throws FileNotFoundException, ParseException {
		  
		  JSONObject scndObj = testData.getJSONObject(1);
		  
		  // returnData method returns computed data
		  JSONObject computedData = rd.returnData().getJSONObject("test2");
		  int expectedScore = scndObj.getInt("score");

		  int expectedIpCount = scndObj.getInt(ipOne);	  
		  // assert the score is equal to 9
		  assertEquals(computedData.getInt("score"), expectedScore);

		  // assert ipCount is 1
		  assertEquals(computedData.getInt(ipOne), expectedIpCount);
	  }
}
