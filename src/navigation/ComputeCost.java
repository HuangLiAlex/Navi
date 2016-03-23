package navigation;

import java.util.ArrayList;

public class ComputeCost {
	public static float computeSingleSegCost(Segments segment) {
		return segment.getDistance() / (segment.getSpeed() / segment.getCongesLvl());
	}
	
	public static void computeAllSegCost() {
		ArrayList<Segments> segments = SegmentList.getInstance();
		for(Segments s: segments){
			float cost = s.getDistance() / (s.getSpeed() / s.getCongesLvl());
			s.setCost(cost);
		}
	}
}
