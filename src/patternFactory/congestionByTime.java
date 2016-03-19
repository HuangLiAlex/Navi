package patternFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

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
            JSONArray details = (JSONArray) jsonObject.get("details");
            
            for(int i=0; i<details.size(); i++){
            	JSONObject detail = (JSONObject) details.get(i);
            	long fromNode = (long) detail.get("fromNode");
            	long toNode = (long) detail.get("toNode");
            	long congestLvl = (long) detail.get("congestLvl");
            	long time = (long) detail.get("time");
            	
            	System.out.printf("from %d, to %d, conges: %d, time: %d\n", fromNode, toNode, congestLvl, time);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}
}