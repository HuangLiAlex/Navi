package patternFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import navigation.Segments;
import navigation.SegmentList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class congestionByTime implements InputPattern {

	@Override
	public void readMap() {
		ArrayList<Segments> segments = SegmentList.getInstance();
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("congestionByTime.json"));

			JSONObject jsonObject = (JSONObject) obj;

			int time = (int) jsonObject.get("time");
			
			int fromNode = (int) jsonObject.get("fromNode");
			int toNode = (int) jsonObject.get("toNode");
			
			int congestLvl = (int) jsonObject.get("congestLvl");


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
