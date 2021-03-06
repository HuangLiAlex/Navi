package patternFactory;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import navigation.AdjList;
import navigation.ComputeCost;
import navigation.SegmentList;
import navigation.Segments;
import navigation.TimeCalculate;
import navigation.Utilisation;

public class CongestionByTime implements InputPattern {	
	@Override
	public void readMap(String requestedTime) {
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		ArrayList<Segments> segments = SegmentList.getInstance();
		JSONParser parser = new JSONParser();
		 
        try {
            Object obj = parser.parse(new FileReader("congestionByTime.json"));
            
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject details = (JSONObject) jsonObject.get("details");
        	JSONArray time = (JSONArray) details.get(requestedTime);		// get congestion condition by time

    		// get info from JSON file and update the segment arraylist
    		for(int j=0; j<time.size(); j++){
         		JSONObject item = (JSONObject) time.get(j);
         		int fromNode = (int) ((long) item.get("fromNode"));
             	int toNode = (int) ((long) item.get("toNode"));
             	int congesLvl = (int) ((long) item.get("congestLvl"));
//                 	System.out.printf("from %d, to %d, conges: %d\n", fromNode, toNode, congesLvl);
             	
             	for(Segments segment: adjList.get(fromNode)){
    				if(segment.getNode2() == toNode){
    					int prevCongesLvl = segment.getCongesLvl();
    					int newCongesLvl = (prevCongesLvl + congesLvl)/2;
    					segment.setCongesLvl(newCongesLvl);
    					Utilisation.updateUtilisation(segment, newCongesLvl);
    					break;
    				}
    			}
         	}
    		
    		// compute cost for all segments
    		ComputeCost.computeAllSegCost();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}