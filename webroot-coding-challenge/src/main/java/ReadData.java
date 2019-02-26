import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadData {
	
	public JSONObject returnData() throws FileNotFoundException, ParseException {

		File file = new File("data.txt");
		JSONObject idData = new JSONObject();

		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;

		try {
			while ((st = br.readLine()) != null) {
				
				if(!st.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(st);
					String id = (String) jsonObj.get("id");
					int theScore = (Integer) jsonObj.get("score");
					String theIp = (String) jsonObj.get("ip");
					
					if(idData.isNull(id)){
						
						idData.put(id, new JSONObject());
						idData.getJSONObject(id).put("score", theScore);
						idData.getJSONObject(id).put(theIp, 1);
						
					}
					else{
						idData.getJSONObject(id).put("score", idData.getJSONObject(id).getInt("score") + theScore);
						
						try{
							idData.getJSONObject(id).put(theIp, idData.getJSONObject(id).getInt(theIp) + 1);
						}
						catch(JSONException e){
							idData.getJSONObject(id).put(theIp, 1);
						}
					}
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idData;
	}
}
