package patternFactory;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import navigation.AdjList;
import navigation.ComputeCost;
import navigation.Segments;
import navigation.Utilisation;

public class CongestionByIncident implements InputPattern{

	@Override
	public void readMap(String requestedTime) {
		ArrayList<ArrayList<Segments>> adjList = AdjList.getInstance();
		JSONParser parser = new JSONParser();
		 
        try {
            Object obj = parser.parse(new FileReader("congestedByIncident.json"));
            
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject details = (JSONObject) jsonObject.get("details");
        	JSONArray time = (JSONArray) details.get(requestedTime);

    		// get info from JSON file and update the segment arraylist
    		for(int j=0; j<time.size(); j++){
         		JSONObject item = (JSONObject) time.get(j);
         		int fromNode = (int) ((long) item.get("fromNode"));
             	int toNode = (int) ((long) item.get("toNode"));
             	int congesLvl = (int) ((long) item.get("congestLvl"));
//                 	System.out.printf("from %d, to %d, conges: %d\n", fromNode, toNode, congesLvl);
             	
             	for(Segments segment: adjList.get(fromNode)){
    				if(segment.getNode2() == toNode){
    					segment.setCongesLvl(congesLvl);
    					Utilisation.updateUtilisation(segment, congesLvl);
    		    		// compute cost for changed segments
    		    		ComputeCost.computeSegCost(segment);
    					break;
    				}
    			}
         	}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
