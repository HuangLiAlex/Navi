package navigation;

import java.util.ArrayList;

public class ComputeCost {
//	public static float computeSingleSegCost(Segments segment) {
//		return segment.getDistance() / (segment.getSpeed() / segment.getCongesLvl());
//	}
	
	public static void computeAllSegCost() {
		ArrayList<Segments> segments = SegmentList.getInstance();
		for(Segments s: segments){
			computeSpeed(s);
			float cost = s.getDistance() / s.getSpeed();
			s.setCost(cost);
		}
	}
	
	public static void computeSegCost(Segments segment) {
			computeSpeed(segment);
			float cost = segment.getDistance() / segment.getSpeed();
			segment.setCost(cost);	
	}
	
	private static void computeSpeed(Segments segment){
		if(segment.getType().equals("express")){
			if(segment.getUtilisation() >= 100){
				segment.setSpeed(convertSpeed(20));
			}else if(segment.getUtilisation() > 40){
				segment.setSpeed(convertSpeed((float) ((100 - segment.getUtilisation())/60.0 * 20 + 20)));
			}else{
				segment.setSpeed(convertSpeed(80));
			}
		}else{		// normal road
			if(segment.getUtilisation() >= 100){
				segment.setSpeed(convertSpeed(10));
			}else if(segment.getUtilisation() > 40){
				segment.setSpeed(convertSpeed((float) ((100 - segment.getUtilisation())/60.0 * 10 + 10)));
			}else{
				segment.setSpeed(convertSpeed(40));
			}
		}
	}
	
	private static float convertSpeed(float speed){
		return (float) (speed * 1000/60.0);
	}
}
