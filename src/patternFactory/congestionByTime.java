package patternFactory;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import navigation.SegmentList;
import navigation.Segments;

public class congestionByTime implements InputPattern {

	@Override
	public void readMap() {
		ArrayList<Segments> segments = SegmentList.getInstance();
		
		JSONParser parser = new JSONParser();
		 
        try {
 
            Object obj = parser.parse(new FileReader("congestionByTime.json"));
            
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject details = (JSONObject) jsonObject.get("details");
        	JSONArray time = (JSONArray) details.get("0900");

        	for(int j=0; j<time.size(); j++){
        		JSONObject item = (JSONObject) time.get(j);
        		long fromNode = (long) item.get("fromNode");
            	long toNode = (long) item.get("toNode");
            	long congestLvl = (long) item.get("congestLvl");
            	System.out.printf("from %d, to %d, conges: %d\n", fromNode, toNode, congestLvl);
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}