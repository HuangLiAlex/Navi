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
			if(segment.getCongesLvl() == 2){		// uti = 51 -> 80
				if(segment.getUtilisation()>=70){
					segment.setSpeed((float) (30.67 * 1000/60));
				}else if(segment.getUtilisation()>=60){
					segment.setSpeed((float) (35.33 *1000/60));
				}
			}else if(segment.getCongesLvl() == 3){
				if(segment.getUtilisation()>=90){
					segment.setSpeed((float) (23 * 1000/60));
				}
			}else{
				segment.setSpeed((float) (80*1000/60)/segment.getCongesLvl());
			}
		}else{		// normal roal
			if(segment.getCongesLvl() == 2){		// uti = 51 -> 80
				if(segment.getUtilisation()>=70){
					segment.setSpeed((float) (16 * 1000/60));
				}else if(segment.getUtilisation()>=60){
					segment.setSpeed((float) (18 *1000/60));
				}
			}else if(segment.getCongesLvl() == 3){
				if(segment.getUtilisation()>=90){
					segment.setSpeed((float) (12 * 1000/60));
				}
			}else{
				segment.setSpeed((float) (40*1000/60)/segment.getCongesLvl());
			}
		}
	}
}
